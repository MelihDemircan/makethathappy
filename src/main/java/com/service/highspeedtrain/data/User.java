package com.service.highspeedtrain.data;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "All details about the user")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2 , message = "Name should have ateast 2 characters")
	private String name;

	@Past
	@ApiModelProperty(notes = "Birth date should be in the past")
	private Date date;

	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Post> posts;
}