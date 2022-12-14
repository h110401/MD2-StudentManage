package service.classroom;

import config.Config;
import model.Classroom;

import java.util.ArrayList;
import java.util.List;

public class ClassroomServiceIMPL implements IClassroomService {

    static String PATH = "src/data/classroomList.txt";

    static Config<List<Classroom>> config = new Config<>();
    static List<Classroom> classroomList = config.read(PATH);

//    static {
//        classroomList.add(new Classroom(1, "JV062022"));
//        classroomList.add(new Classroom(2, "JS072022"));
//
//        config.write(classroomList, PATH);
//    }

    @Override
    public List<Classroom> findAll() {
        return classroomList;
    }

    @Override
    public void save(Classroom classroom) {
        findById(classroom.getId());
        if (findById(classroom.getId()) == null) {
            classroomList.add(classroom);
        } else {
            Classroom classEdit = findById(classroom.getId());
            classEdit.setName(classroom.getName());
        }
    }

    @Override
    public Classroom findById(int id) {
        for (Classroom classroom : classroomList) {
            if (classroom.getId() == id) {
                return classroom;
            }
        }
        return null;
    }

    @Override
    public void remove(int id) {
        Classroom classroomDeleted = findById(id);
        classroomList.remove(classroomDeleted);
    }


}
