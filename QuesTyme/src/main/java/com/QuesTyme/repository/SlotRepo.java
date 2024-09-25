package com.QuesTyme.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.QuesTyme.entity.Slot;

public interface SlotRepo extends JpaRepository<Slot, Integer> {
	@Query("SELECT e FROM Slot e WHERE e.startTime =:startTime")
	List<Slot> findByStartTime(@Param("startTime") LocalTime startTime) ;

	@Query("SELECT s FROM Slot s where s.date=:date")
	List<Slot> findByLocalDate(@Param("date") LocalDate date);

	@Query("SELECT e FROM Slot e WHERE e.date =:date AND (e.status='U' OR e.status='B') AND e.adminId =:adminId")
	List<Slot> FindBydateStatusAdminId(@Param("date") LocalDate date, @Param("adminId") Integer adminId);

	@Query("SELECT e FROM Slot e WHERE e.date >= CURRENT_DATE AND e.status='B' AND e.adminId =:adminId")
	List<Slot> FindBydateStatusBAdminId(@Param("adminId") Integer adminId);

	@Query("SELECT e FROM Slot e WHERE e.date >= CURRENT_DATE AND e.adminId =:adminId AND (e.status='U' OR e.status='B')"  )
	List<Slot> FindByAdminId(@Param("adminId") Integer adminId);
	
	@Query("SELECT e FROM Slot e WHERE e.adminId =:adminId AND (e.status='U' OR e.status='B')"  )
	List<Slot> FindByAllSlotsAdminId(@Param("adminId") Integer adminId);

	@Query("SELECT DISTINCT e.date FROM Slot e where e.adminId =:adminId AND e.date >= CURRENT_DATE")
	List<LocalDate> findAllDistinctDates(@Param("adminId") Integer adminId);

	@Query("SELECT e FROM Slot e WHERE e.date =:date AND e.status='U' AND e.adminId =:adminId")
	List<Slot> FindBydateAndAdminId(@Param("date") LocalDate date, @Param("adminId") Integer adminId);

	@Query("SELECT DISTINCT e.type FROM Slot e where  e.date >= CURRENT_DATE")
	List<String> findAllDistincttypes();

	@Query("SELECT DISTINCT e.adminId FROM Slot e where e.type=:type AND  e.date >= CURRENT_DATE")
	List<Integer> findAllAdminByType(@Param("type") String type);
	
	@Query("SELECT  me.status, COUNT(me.status) FROM Slot me WHERE me.date >= CURRENT_DATE AND me.adminId =:adminId  GROUP BY me.status")
	List<Object[]> FindNumberOfSlotsByAdminId(@Param("adminId") Integer adminId);
	
	@Query("SELECT  me.status, COUNT(me.status) FROM Slot me WHERE me.date >= CURRENT_DATE  GROUP BY me.status")
	List<Object[]> FindSlotsAnalytics();
	
	
	@Query(value ="select e.slot_id from questyme.slot e where e.recurring_id=:id and e.status='U'" ,  nativeQuery = true)
	List<Integer> getSlotByRecurringId(@Param("id") Integer RecurringId);

	
	

//	List<Slot> findSlotByDateAndTime(Integer adminId, LocalDate date, LocalTime startTime, LocalTime endTime);


//	@Query("select s from Slot s where s.date = :1 AND (s.startTime=?2 AND s.endTime=?3) AND s.status='B'")
//	Slot findBookedSlotsByDateAndTime(LocalDate date, LocalTime startTime, LocalTime endTime); 
//	
////	
//	@Query("SELECT s FROM Slot s WHERE (s.status='B' AND (s.adminId = ?1 OR s.userId=?1 ) AND s.date = ?2 AND ((s.startTime >= ?3 AND s.startTime < ?4))")
//	List<Slot> findSlotByDateAndTime(int adminID, LocalDate date, LocalTime startTime, LocalTime endTime);
//	
	
	List<Slot> findByDateAndAdminId(LocalDate date, Integer adminid);
}
