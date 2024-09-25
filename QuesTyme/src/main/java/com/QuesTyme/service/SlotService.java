package com.QuesTyme.service;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuesTyme.dto.SlotTiming;
import com.QuesTyme.entity.Interviews;
import com.QuesTyme.entity.OneOnOne;
import com.QuesTyme.entity.Slot;
import com.QuesTyme.entity.User;
import com.QuesTyme.exceptions.ApiException;
import com.QuesTyme.exceptions.InterviewException;
import com.QuesTyme.exceptions.InvalidTimeException;
import com.QuesTyme.exceptions.ResourceNotFoundException;
import com.QuesTyme.repository.InterviewRepo;
import com.QuesTyme.repository.SlotRepo;
import com.QuesTyme.repository.UserRepository;

@Service
public class SlotService {

	@Autowired
	private SlotRepo slotRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InterviewRepo interviewRepo;

	@Autowired
	private EmailService emailService;

	public String deleteSlotByDate(Integer adminid, LocalDate date) {
		List<Slot> slots = slotRepo.findByDateAndAdminId(date, adminid);
		if(slots.size()==0) {
			return "No slots available";
		}
		for (Slot slot : slots) {
			if (slot.getStatus() == 'B') {
				continue;
			} else {
				slot.setStatus('U');
				slotRepo.save(slot);
			}
		}
		
		String Subject = "Slot deleted Successfully";
		String Body = "Dear Admin, all the slot deleted successfully!";
		emailService.sendEmail("admin", Subject, Body);
		return "slots deleted, ok";
		  
	}

	public String createOneOnOneSlots(OneOnOne oneOnOne) {

		int slotsToBeCreated = 0;

		List<SlotTiming> slotTime = oneOnOne.getSlotTime();
		if (slotTime.size() == 0) {
			throw new ApiException("Time is Required !");
		} else {
			List<Slot> slotlist = new ArrayList<>();
			for (int i = 0; i < slotTime.size(); i++) {

				LocalTime startTime = slotTime.get(i).getStartTime();
				LocalTime endTime = slotTime.get(i).getEndTime();

				Duration duration = Duration.between(startTime, endTime);

				int minutes = (int) duration.toMinutes();

				slotsToBeCreated += (minutes / oneOnOne.getDuration());

				if (startTime.compareTo(endTime) > 0) {
					throw new InvalidTimeException("start time must be less than end time");
				}

				while (startTime.compareTo(endTime) != 0) {
					Slot makeSlot = new Slot();
					makeSlot.setTitle(oneOnOne.getTitle());
					makeSlot.setInstruction(oneOnOne.getInstruction());
					makeSlot.setMeetingLink(oneOnOne.getMeetingLink());
					makeSlot.setAdminId(oneOnOne.getAdminId());
					makeSlot.setDate(oneOnOne.getDate());
					makeSlot.setType(oneOnOne.getType());
					makeSlot.setStatus('U');

					// edit startTime
					makeSlot.setStartTime(startTime);
					startTime = startTime.plusMinutes(oneOnOne.getDuration());
					makeSlot.setEndTime(startTime);

//					List<Interviews> existingInterviews = interviewRepo.findByInterview(oneOnOne.getAdminId(), makeSlot.getDate(),makeSlot.getStartTime(), makeSlot.getEndTime());

//					if (!existingInterviews.isEmpty()) {
//						throw new InterviewException("Interviewee is already booked for the given date and time");
//					}
 
					List<Slot> existingSlots = slotRepo.findSlotByDateAndTime(oneOnOne.getAdminId(), makeSlot.getDate(),
							makeSlot.getStartTime(), makeSlot.getEndTime());
//
					if (!existingSlots.isEmpty()) {
						throw new InvalidTimeException("Slots are existing in the interview booked timings...");
					}

					System.out.println(existingSlots);

					try {
						if (existingSlots.size() == 0) {
							slotlist.add(makeSlot);
						} else {
							throw new InvalidTimeException("Slot already exists");
						}
					} catch (InvalidTimeException ite) {
						System.out.println(ite.getMessage());
					}

					List<Interviews> interviews = interviewRepo.findInterviewsByDateAndTimeForAnAdmin(
							oneOnOne.getAdminId(), makeSlot.getDate(), makeSlot.getStartTime(), makeSlot.getEndTime());

					if (interviews != null) {
						return "Interviews exist during the given time slots";
					}
 
//					slotRepo.save(makeSlot);

				}
			}

			slotRepo.saveAll(slotlist);

			if (slotlist.size() == slotsToBeCreated)
				return "Slots created Successfully";

			return "Slots created Partially or no slots created due to overlapment of slots";

		}

	}

	/**
	 * 
	 * This method retrieves all the users from the UserRepository and stores them
	 * in a Map with the email as the key and the User object as the value.
	 * 
	 * @return A Map<String, User> containing all the users from the UserRepository.
	 */
	public Map<Integer, User> userMap() {
		Map<Integer, User> map = new HashMap<>();
		userRepository.findAll().forEach(user -> map.put(user.getId(), user));
		return map;
	}

	public String Bookslot(Integer userId, Integer slotId) {

		String response;
		Slot slot = slotRepo.findById(slotId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", slotId));
		if (slot.getStatus() == 'U') {
			slot.setUserId(userId);
			slot.setStatus('B');
			slotRepo.save(slot);

			Interviews interview = new Interviews();

			interview.setInterviewerId(slot.getAdminId());
			interview.setIntervieweeId(slot.getUserId());
			interview.setStartTime(slot.getStartTime());
			interview.setEndTime(slot.getEndTime());
			interview.setDate(slot.getDate());
			interview.setInstructions(slot.getInstruction());

			interview.setMeetingLink(slot.getMeetingLink());
			interview.setTitle(slot.getTitle());
			interview.setMeetingStatus("P");
			interview.setCategory(slot.getType());

			interviewRepo.save(interview);

			Map<Integer, User> user = userMap();
			User interviewer = user.get(interview.getInterviewerId());
			User interviewee = user.get(interview.getIntervieweeId());

			// Send an email to the interviewer to notify them about the scheduled
			// interview.
			String interviewerSubject = "Interview Scheduled Successfully";
			String interviewerBody = "Dear " + interviewer.getName() + ",\n\nYour interview with "
					+ interviewee.getName() + " has been scheduled for " + interview.getDate() + " from "
					+ interview.getStartTime() + " to " + interview.getEndTime()
					+ ".\n\nPlease use the following link to join the meeting: " + interview.getMeetingLink()
					+ "\n\n Or you can visit the interview platform" + "\n\nBest regards,\nThe Interview Team";
			emailService.sendEmail(interviewer.getEmail(), interviewerSubject, interviewerBody);
//			// Send an email to the interviewee to notify them about the scheduled
//			// interview.
			String intervieweeSubject = "Interview Scheduled Successfully";
			String intervieweeBody = "Dear " + interviewee.getName() + ",\n\nYour interview with "
					+ interviewer.getName() + " has been scheduled for " + interview.getDate() + " from "
					+ interview.getStartTime() + " to " + interview.getEndTime()
					+ ".\n\nPlease use the following link to join the meeting: " + interview.getMeetingLink()
					+ "\n\n Or you can visit the interview platform" + "\n\nBest regards,\nThe Interview Team";
			emailService.sendEmail(interviewee.getEmail(), intervieweeSubject, intervieweeBody);

			response = "Slot Booked";

		} else {
			response = "Slot Already Booked";
		}
		return response;

	}

	public String DeleteSlot(Integer slotId) {

		Slot slot = slotRepo.findById(slotId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", slotId));

		slot.setStatus('D');
		slotRepo.save(slot);

//		slotRepo.delete(slot);

		return "slot deleted";

	}

	// This method provide slots using adminId and today and upcomings slots not
	// previous

	public List<Slot> getAllSlotByStartTime(LocalTime st) {
		return slotRepo.findByStartTime(st);
	}

	public List<Slot> getAllSlotByAdminId(LocalDate date, Integer adminId) {
		return slotRepo.FindBydateStatusAdminId(date, adminId);
	}

	public List<Slot> getBookedSlotByAdminId(Integer adminId) {
		return slotRepo.FindBydateStatusBAdminId(adminId);
	}

	public List<Slot> getSlotByAdminId(Integer adminId) {
		return slotRepo.FindByAdminId(adminId);
	}

	public List<Slot> getAllSlotByAdminId(Integer adminId) {
		return slotRepo.FindByAllSlotsAdminId(adminId);
	}

	public List<LocalDate> getAdminAndDates(Integer adminId) {
		return (List<LocalDate>) slotRepo.findAllDistinctDates(adminId);

	}

	public List<Slot> getUnbookedSlotByDateAndAdminId(LocalDate date, Integer adminId) {
		return slotRepo.FindBydateAndAdminId(date, adminId);

	}

	public Map<String, List<String>> getAllAvailableTypes() {
		Map<String, List<String>> hashMap = new HashMap<>();
		List<String> types = slotRepo.findAllDistincttypes();
		hashMap.put("type", types);
		return hashMap;

	}

	public Map<String, List<User>> getAllAdminByType(String type) {
		Map<String, List<User>> hashMap = new HashMap<>();
		List<Integer> adminIds = slotRepo.findAllAdminByType(type);
		List<User> users = userRepository.findByIds(adminIds);
		hashMap.put("Instructors", users);
		return hashMap;
	}

	public Map<String, Object> getSlotAnalyticsByAdminId(Integer adminId) {
		List<Object[]> data = slotRepo.FindNumberOfSlotsByAdminId(adminId);

		List<Map<String, Object>> jsonResults = new ArrayList<>();
		Long totalSlots = (long) 0;

		for (Object[] row : data) {
			Long count = (Long) row[1];
			Character meetingStatus = (Character) row[0];
			totalSlots += count;
			Map<String, Object> jsonRow = new HashMap<>();
			jsonRow.put("count", count);
			jsonRow.put("meetingStatus", meetingStatus);

			jsonResults.add(jsonRow);
		}
		Map<String, Object> jsonResult = new HashMap<>();
		jsonResult.put("totalSlots", totalSlots);
		jsonResult.put("results", jsonResults);

		return jsonResult;
	}

	public Map<String, Object> getSlotAnalytics() {
		List<Object[]> data = slotRepo.FindSlotsAnalytics();

		List<Map<String, Object>> jsonResults = new ArrayList<>();
		Long totalSlots = (long) 0;

		for (Object[] row : data) {
			Long count = (Long) row[1];
			Character meetingStatus = (Character) row[0];
			totalSlots += count;
			Map<String, Object> jsonRow = new HashMap<>();
			jsonRow.put("count", count);
			jsonRow.put("meetingStatus", meetingStatus);
			jsonResults.add(jsonRow);
		}
		Map<String, Object> jsonResult = new HashMap<>();
		jsonResult.put("totalSlots", totalSlots);
		jsonResult.put("results", jsonResults);

		return jsonResult;
	}
}
