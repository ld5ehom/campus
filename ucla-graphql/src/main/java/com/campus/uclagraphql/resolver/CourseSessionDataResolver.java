package com.campus.uclagraphql.resolver;

import com.campus.uclagraphql.model.CourseSession;
import com.campus.uclagraphql.model.CourseSessionFile;
import com.campus.uclagraphql.service.FileService;
import com.campus.uclagraphql.service.dummy.DummyFileService;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class CourseSessionDataResolver {

    private final FileService fileService;

    public CourseSessionDataResolver(FileService fileService) {
        this.fileService = fileService;
    }

    @SchemaMapping(typeName = "CourseSession", field = "files")
    public List<CourseSessionFile> getFiles(CourseSession courseSession) {
        return fileService.findFilesBySessionId(courseSession.getId());
    }
}