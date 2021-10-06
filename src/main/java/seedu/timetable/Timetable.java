package seedu.timetable;

import seedu.module.Module;
import seedu.ui.TextUi;
import seedu.ui.TimetableUI;

import java.time.DayOfWeek;
import java.util.ArrayList;

/**
 * The Timetable Class, which will track all added modules and lessons that you have signed up for!
 * Each cell in the timetable will display the module code, lesson type and venue of the lesson
 * Each Timetable should be assigned a semester number --> Academic Year semester (1 OR 2)
 */
public class Timetable {

    private static final int DEFAULT_START = 9;
    private static final int DEFAULT_END = 16;

    private int semester;
    private int earliestHour;
    private int latestHour;

    private ArrayList<Module> modules;

    private TimetableLesson[] monday = new TimetableLesson[24];
    private TimetableLesson[] tuesday = new TimetableLesson[24];
    private TimetableLesson[] wednesday = new TimetableLesson[24];
    private TimetableLesson[] thursday = new TimetableLesson[24];
    private TimetableLesson[] friday = new TimetableLesson[24];
    private TimetableLesson[] saturday = new TimetableLesson[24];
    private TimetableLesson[] sunday = new TimetableLesson[24];

    /**
     * Creates a Timetable assigned to a specific semester of the Academic Year
     * @param semester Semester 1 OR 2 of the Academic Year
     */
    public Timetable(int semester) {
        this.modules = new ArrayList<>();
        this.semester = semester;
        this.earliestHour = DEFAULT_START;
        this.latestHour = DEFAULT_END;
    }

    /**
     * Adds a Timetable Lesson to the timetable,
     * and adds the corresponding module to an internal list if not already added
     * This can be a Lecture, Tutorial or Laboratory
     * @param timetableLesson lesson to be added to the timetable
     * @see TimetableLesson
     */
    public void addLesson(TimetableLesson timetableLesson) {

        switch (timetableLesson.getDayOfWeek()) {
        case MONDAY:
            addLessonToSchedule(timetableLesson, monday);
            break;
        case TUESDAY:
            addLessonToSchedule(timetableLesson, tuesday);
            break;
        case WEDNESDAY:
            addLessonToSchedule(timetableLesson, wednesday);
            break;
        case THURSDAY:
            addLessonToSchedule(timetableLesson, thursday);
            break;
        case FRIDAY:
            addLessonToSchedule(timetableLesson, friday);
            break;
        case SATURDAY:
            addLessonToSchedule(timetableLesson, saturday);
            break;
        case SUNDAY:
            addLessonToSchedule(timetableLesson, sunday);
            break;
        default:
            break;
        }

        if (timetableLesson.getStartHour() < earliestHour) {
            earliestHour = timetableLesson.getStartHour();
        }

        if (timetableLesson.getEndHour() > latestHour) {
            latestHour = timetableLesson.getEndHour();
        }
    }

    /**
     * Adds a lesson to a specific day schedule
     * E.g. addLessonToSchedule(lesson, monday) will add the lesson to the monday schedule
     * @param timetableLesson Lesson to be added to a day's schedule
     * @param schedule Day's schedule (i.e monday/tuesday/.. etc) to add the lesson to
     */
    private void addLessonToSchedule(TimetableLesson timetableLesson, TimetableLesson[] schedule) {
        int start = timetableLesson.getStartHour();
        int end = timetableLesson.getEndHour();
        for (int i = start; i < end; i++) {
            schedule[i] = timetableLesson;
        }
        addModuleToList(timetableLesson.getModule());
    }

    /**
     * Adds the lesson's module to the internal tracking list
     * This is to be displayed later
     * @param module Module to be added
     * @see Module
     */
    private void addModuleToList(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
        }
    }

    /**
     * Displays the timetable over Command Line Interface
     * Draws a grid where the Horizontal Axis represents the hour timing
     * and the Vertical Axis represents the Day (MON/TUES/WED/... etc.)
     * Displays the lessons in each grid cell that had been added to the timetable
     *
     * Also displays all the modules taken and the total number of MCs taken for the
     * timetable
     */
    public void showTimetable() {
        TimetableUI.printScheduleHours(earliestHour, latestHour);
        TimetableUI.printDaySchedule("MON", monday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("TUE", tuesday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("WED", wednesday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("THU", thursday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("FRI", friday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("SAT", saturday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("SUN", sunday, earliestHour, latestHour);
        TimetableUI.printModules(modules);
    }

    public TimetableLesson getLesson(DayOfWeek day, int startHour) {
        switch (day) {
        case MONDAY:
            return monday[startHour];
        case TUESDAY:
            return tuesday[startHour];
        case WEDNESDAY:
            return wednesday[startHour];
        case THURSDAY:
            return thursday[startHour];
        case FRIDAY:
            return friday[startHour];
        case SATURDAY:
            return saturday[startHour];
        case SUNDAY:
            return sunday[startHour];
        default:
            return null;
        }
    }

}