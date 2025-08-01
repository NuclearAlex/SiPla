package by.homiel.shutov.sipla_web.service.delete;

import org.springframework.stereotype.Service;

@Service
public class FileDeleteService {

    public String deleteDocument(String docName) {

        return "The document " + docName + " has been deleted";
    }
}
