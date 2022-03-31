package net.developia.spring02.mvc1;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// @Data = @Getter + @Setter + @ToString
@AllArgsConstructor // 모든 가능한 생성자
@NoArgsConstructor // 매개변수가 없는 생성자
public class MemoDTO implements Serializable {
	@Length(min=2, max=6)
	private String name;
	
	@NotBlank
	private String title;
	
	@Length(min=2)
	private String password;
	
	@Length(min=1)
	private String content;
}
