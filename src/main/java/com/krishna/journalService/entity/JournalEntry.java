package com.krishna.journalService.entity;

import com.krishna.journalService.enums.Sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private ObjectId id;

    @NonNull
    private String title;

    @NonNull
    private String content;

    @CreatedDate
    private LocalDateTime date;

    private Sentiment sentiment;
}
