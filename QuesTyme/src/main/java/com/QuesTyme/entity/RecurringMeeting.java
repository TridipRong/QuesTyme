package com.QuesTyme.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recurring_meeting")
public class RecurringMeeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recurring_id")
	private int recurringId;
	
	private int adminId;

	private String title;

	@Column(name = "meeting_link")
	private String meetingLink;

	private String instruction;

	private int duration;

	private String type;

	@JsonManagedReference
	@javax.persistence.OneToMany
	private List<Availability> availabilities;

}