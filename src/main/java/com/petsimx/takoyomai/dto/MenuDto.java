package com.petsimx.takoyomai.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.petsimx.takoyomai.model.FileData;
import com.petsimx.takoyomai.model.Menu;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDto {
	
	private Menu menu = new Menu();
	private MultipartFile file;
	private List<byte[]> bytes = new ArrayList<byte[]>();
	private List<Menu> menus = new ArrayList<Menu>();
	private List<String> contentTypes = new ArrayList<String>();
	private List<MenuDto> menuDtos = new ArrayList<MenuDto>();
	
	private long menuId;
	private String name;
	private String description;
	private double price;
	private String type;
	private boolean isToday;
	private byte[] fileByte;
	private String fileType;
	
	public MenuDto(long menuId, String name, String description, double price, String type, byte[] fileByte, String fileType, boolean isToday) {
		this.menuId = menuId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.type = type;
		this.fileByte = fileByte;
		this.fileType = fileType;
		this.isToday = isToday;
	}

	@Override
	public String toString() {
		return "MenuDto [menuId=" + menuId + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", type=" + type + ", isToday=" + isToday + ", fileType=" + fileType + "]";
	}
}
