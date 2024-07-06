package com.campus.uclagraphql.controller;

import com.campus.uclagraphql.model.EventLog;
import com.campus.uclagraphql.model.PlaybackRecord;
import com.campus.uclagraphql.service.PlaybackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PlaybackController {
    private final PlaybackService playbackService;

    @Autowired
    public PlaybackController(PlaybackService playbackService) {
        this.playbackService = playbackService;
    }

    @MutationMapping
    public PlaybackRecord startRecord(@Argument Long userId, @Argument Long fileId) {
        return playbackService.startRecord(userId, fileId);
    }

    @MutationMapping
    public PlaybackRecord endRecord(@Argument Long recordId) {
        return playbackService.endRecord(recordId);
    }

    @MutationMapping
    public EventLog logEvent(@Argument Long recordId, @Argument Long userId,
                             @Argument String eventType, @Argument String timestamp) {
        EventLog eventLog = new EventLog(null, recordId, userId, eventType, timestamp);
        return playbackService.logEvent(eventLog);
    }
}