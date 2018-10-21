package org.portafolio.safe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class RequestDto<T> {

	@Getter @Setter T object;
	@Getter @Setter String message = "";
}
