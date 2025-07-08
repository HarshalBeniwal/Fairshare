package com.harshal.beniwal.fairshare.model.expense;

import com.harshal.beniwal.fairshare.model.user.UserResponseDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseDTO {
    Long id;
    String description;
    Double amount;
    String splitMode;
    UserResponseDTO group;
}
