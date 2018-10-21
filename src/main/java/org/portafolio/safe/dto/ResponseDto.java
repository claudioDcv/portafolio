package org.portafolio.safe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

	@Getter @Setter T object;
	@Getter @Setter String message = "";

}
