package com.QuesTyme.changes;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.QuesTyme.exceptions.InterviewException;
import com.opencsv.exceptions.CsvException;

public class CreateCsvMethod {
	
	
//	public String createInterviewsFromCsv(MultipartFile file) throws InterviewException, IOException, CsvException {
//	    CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
//	    List<String[]> rows = csvReader.readAll();
//	    csvReader.close();
//
//	    List<Interviews> interviews = new ArrayList<>();
//	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//	    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//	    Map<String, User> user = userMap();
//	    ExecutorService executor = Executors.newFixedThreadPool(10); // Create a thread pool with 10 threads
//	    for (int i = 1; i < rows.size(); i++) {
//	        String[] row = rows.get(i);
//	        Interviews interview = new Interviews();
//	        User interviewer = user.get(row[0]);
//	        User interviewee = user.get(row[1]);
//	        if (interviewer != null) {
//	            interview.setInterviewerId(interviewer.getId());
//	        }
//	        if (interviewee != null) {
//	            interview.setIntervieweeId(interviewee.getId());
//	        }
//	        interview.setStartTime(LocalTime.parse(row[2], timeFormatter));
//	        interview.setEndTime(LocalTime.parse(row[3], timeFormatter));
//	        interview.setDate(LocalDate.parse(row[4], dateFormatter));
//	        interview.setCategory(row[5]);
//	        interview.setInstructions(row[6]);
//	        interview.setTitle(row[7]);
//	        interview.setMeetingLink(row[8]);
//	        interview.setMeetingStatus(row[9]);
//	        interview.setBatch(row[10]);
//
//	        List<Interviews> existingInterviews = interviewrepo.findByInterview(interview.getIntervieweeId(),
//	                interview.getDate(), interview.getStartTime(), interview.getEndTime(),
//	                interview.getInterviewerId());
//	        if (!existingInterviews.isEmpty()) {
//	            System.out.println("Interviewee " + interviewee.getEmail()
//	                    + " is already booked for the given date and time, skipping row " + i);
//	            continue;
//	        }
//	        interviews.add(interview);
//
//	        String interviewerSubject = "Interview Scheduled Successfully";
//	        String interviewerBody = "Dear " + interviewer.getName() + ",\n\nYour interview with "
//	                + interviewee.getName() + " has been scheduled for " + interview.getDate() + " from "
//	                + interview.getStartTime() + " to " + interview.getEndTime()
//	                + ".\n\nPlease use the following link to join the meeting: " + interview.getMeetingLink()
//	                + "\n\n Or you can visit the interview platform" + "\n\nBest regards,\nThe Interview Team";
//	        Runnable sendInterviewerEmailTask = () -> emailService.sendEmail(row[1], interviewerSubject, interviewerBody);
//	        executor.execute(sendInterviewerEmailTask);
//
//	        String intervieweeSubject = "Interview Scheduled Successfully";
//	        String intervieweeBody = "Dear " + interviewee.getName() + ",\n\nYour interview with "
//	                + interviewer.getName() + " has been scheduled for " + interview.getDate() + " from "
//	                + interview.getStartTime() + " to " + interview.getEndTime()
//	                + ".\n\nPlease use the following link to join the meeting: " + interview.getMeetingLink()
//	                + "\n\n Or you can visit the interview platform" + "\n\nBest regards,\nThe Interview Team";
//	        Runnable sendIntervieweeEmailTask = () -> emailService.sendEmail(row[0], intervieweeSubject, intervieweeBody);
//	        executor.execute(sendIntervieweeEmailTask);
//	    }
//	    interviewrepo.saveAll(interviews);
//	    executor.shutdown(); // Shut down the executor service
//	    while (!executor.isTerminated()) {
//	        Thread.sleep(100); // wait for all threads to finish
//	    }
//	    return "Interview Created Successfully";
//	}
	
	
}
