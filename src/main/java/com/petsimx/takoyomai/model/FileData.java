package com.petsimx.takoyomai.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "FILE_DATA" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileData {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private String type;
	
	@Lob
	@Column( length = 5000000 )
	private byte[] fileByte;
}
