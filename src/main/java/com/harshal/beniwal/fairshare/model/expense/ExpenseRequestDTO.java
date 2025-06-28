package com.harshal.beniwal.fairshare.model.expense;

import com.harshal.beniwal.fairshare.entity.SPLITMODE;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ExpenseRequestDTO {
    private String description;
    private Double amount;
    private SPLITMODE splitmode;
    private UUID groupId;
    private Long payerId;
    private List<Long> participants;
}
