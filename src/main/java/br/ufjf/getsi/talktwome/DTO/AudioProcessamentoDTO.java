package br.ufjf.getsi.talktwome.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AudioProcessamentoDTO {

    @JsonProperty
    private String status;
    @JsonProperty
    private String message;
    @JsonProperty
    private Boolean result;

    public AudioProcessamentoDTO(String status, String message, Boolean result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public AudioProcessamentoDTO() {
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

    
}