package himedia.connectus.domain;

import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
//lombok.NonNull
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class Post extends PostTime {
/*

create table if not exists POST(
	POST_ID serial primary key,
	##serial: auto incrementing integer

	DATE timestamp NOT NULL,

	CONTENT text NOT NULL,

	PLACE varchar(100) NOT NULL,

	IMG_PATH text

	##WRITER
);

*/
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@Nullable
	//private LocalDateTime userDateStr;
	
	@NonNull
	private String title;
	
	//private PostDate date;
	@CreatedDate
	@NonNull
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@NonNull
	private LocalDateTime updatedDate;
	
	@NonNull
	private String content;
	
	@NonNull
	private String place;
	
	@Nullable
	private String filePath;

	@Nullable
	private String fileName;
	
	//public Post() {}
	
	public Post(String title, String content, String place, String filePath, String fileName) {
		//this.userDateStr = userDateStr;//LocalDateTime.parse(userDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.title = title;
		//this.createdDate = LocalDateTime.now();
		//this.updatedDate = LocalDateTime.of(9999, 01, 01, 00, 00, 00, 00);
		this.content = content;
		this.place = place;
		this.filePath = filePath;
		this.fileName = fileName;
	}
}
