package com.QuesTyme.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class interviewDtoOM {

	private String interviewerEmail;
	
    private List<String> intervieweeEmail;
    
    private LocalTime startTime;
    private LocalTime endTime;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private String category;
    private String instructions;
    private String title;
    private String meetingLink;
    private String batch;
}
