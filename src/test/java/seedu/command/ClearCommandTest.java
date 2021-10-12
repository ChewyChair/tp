package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableLesson;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClearCommandTest {

    /**
     * Tests if the timetable gets cleared.
     */

    @Test
    public void execute_timetableWith2modules_timetableShouldBeCleared() {

        // Creating two test timetables
        Timetable tt1 = new Timetable(1);
        Timetable tt2 = new Timetable(1);

        //Creating two test modules and adding to timetable 1
        Module testMod1 = new Module("CFG1002");
        testMod1.setModuleCredit(2.0);
        testMod1.setTitle("Career Catalyst");

        Module testMod2 = new Module("CS1231");
        testMod1.setModuleCredit(4.0);
        testMod1.setTitle("Discrete Structures");

        Lesson lesson1 = new Lesson(
                "CFG1002",
                "0600",
                "0800",
                "E-Learn_B",
                "Lecture",
                "Wednesday");
        Lesson lesson2 = new Lesson(
                "CS1231",
                "1000",
                "1200",
                "E-Learn_C",
                "Lecture",
                "Thursday");
        TimetableLesson timetableLesson1 = new TimetableLesson(testMod1, 1, lesson1);
        TimetableLesson timetableLesson2 = new TimetableLesson(testMod2, 1, lesson2);
        tt1.addLesson(timetableLesson1);
        tt1.addLesson(timetableLesson2);

        //Clearing timetable 1
        Command command = new ClearCommand(tt1);
        command.execute();

        //Comparing the two timetables, flag = 1 represents they are same, and 0 represents vice versa
        //checking if the empty timetable (that is timetable 2 matches with timetable 1)
        int flag = tt2.compareTo(tt1);
        boolean isSame = flag == 1 ? true : false;
        assertTrue(isSame);
    }
}