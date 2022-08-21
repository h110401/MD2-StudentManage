package controller;

import model.Classroom;
import service.classroom.ClassroomServiceIMPL;
import service.classroom.IClassroomService;

import java.util.List;

public class ClassroomController {

    IClassroomService classroomService = new ClassroomServiceIMPL();

    public List<Classroom> getClassroomList() {
        return classroomService.findAll();
    }


    public void saveClassroom(Classroom classroom) {
        classroomService.save(classroom);
    }

    public Classroom getClassroom(int id) {
        return classroomService.findById(id);
    }

    public void editClassroom(Classroom classroom) {
        classroomService.save(classroom);
    }
    public void deleteClassroom(int id) {
        classroomService.remove(id);
    }
}
