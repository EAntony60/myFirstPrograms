package students.logic;
 
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
 
public class ManagementSystem {
 
    private List<Group> groups;
    private Collection<Student> students;
 
    // ��� ������� Singletone ����������� ����������
    private static ManagementSystem instance;
 
    // �������� �����������
    private ManagementSystem() {
        loadGroups();
        loadStudents();
    }
 
    // ����� getInstance - ���������, ���������������� �� �����������
    // ���������� (� ������ ���������� ������ ���) � ���������� ��
    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }
 
    // �����, ������� ���������� ��� ������� ������
    public static void main(String[] args) {
        // ���� ��� ��������� ��� ������������� ����������� ����� � ����
        // �.�. �� ����� ��������� �� ������ ������������� ���������, 
        // ���� � ������ ������ ����� ������
        try {
            System.setOut(new PrintStream("out.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
 
        ManagementSystem ms = ManagementSystem.getInstance();
 
        // �������� ������� ������ �����
        printString("������ ������ �����");
        printString("*******************");
        List<Group> allGroups = ms.getGroups();
        for (Group gi : allGroups) {
            printString(gi);
        }
        printString();
 
        // �������� ������� ������ ���������
        printString("������ ������ ���������");
        printString("***********************");
        Collection<Student> allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();
 
        // ����� ������� ��������� �� �������
        printString("������ ��������� �� �������");
        printString("***************************");
        List<Group> groups = ms.getGroups();
        // ��������� ��� ������
        for (Group gi : groups) {
            printString("---> ������:" + gi.getNameGroup());
            // �������� ������ ��������� ��� ���������� ������
            Collection<Student> students = ms.getStudentsFromGroup(gi, 2006);
            for (Student si : students) {
                printString(si);
            }
        }
        printString();
 
        // �������� ������ �������� � ������� ��� � ������
        Student s = new Student();
        s.setStudentId(5);
        s.setFirstName("�����");
        s.setPatronymic("������������");
        s.setSurName("����������");
        s.setSex('�');
        Calendar c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        printString("���������� ��������:" + s);
        printString("********************");
        ms.insertStudent(s);
        printString("--->> ������ ������ ��������� ����� ����������");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();
 
        // ������� ������ � �������� - ���������� ������ � ��� ����������������
        // �� ��� ��������� ����� ����� �� - ������� �������� � ����� �� ��
        s = new Student();
        s.setStudentId(5);
        s.setFirstName("�����");
        s.setPatronymic("������������");
        s.setSurName("��������������");
        s.setSex('�');
        c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        printString("�������������� ������ ��������:" + s);
        printString("*******************************");
        ms.updateStudent(s);
        printString("--->> ������ ������ ��������� ����� ��������������");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();
 
        // ������ ������ ��������
        printString("�������� ��������:" + s);
        printString("******************");
        ms.deleteStudent(s);
        printString("--->> ������ ������ ��������� ����� ��������");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();
 
        // ����� �� ��������� ���� ��������� ����� ������ � ������
        // �� �����, ��� � ��� 2 ������
        // �� ������ ���������� �������, �� ���� ������� ���
        Group g1 = groups.get(0);
        Group g2 = groups.get(1);
        printString("������� ��������� �� 1-�� �� 2-� ������");
        printString("***************************************");
        ms.moveStudentsToGroup(g1, 2006, g2, 2007);
        printString("--->> ������ ������ ��������� ����� ��������");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();
 
        // ������� ��������� �� ������
        printString("�������� ��������� �� ������:" + g2 + " � 2006 ����");
        printString("*****************************");
        ms.removeStudentsFromGroup(g2, 2006);
        printString("--->> ������ ������ ��������� ����� ��������");
        allStudends = ms.getAllStudents();
        for (Iterator i = allStudends.iterator(); i.hasNext();) {
            printString(i.next());
        }
        printString();
    }
 
    // ����� ������� ��� ������ � �������� �� � ��������� ��� �����
    public void loadGroups() {
        // ��������� - ����� ���� ��� ������ ��� �� ������ ������
        if (groups == null) {
            groups = new ArrayList<Group>();
        } else {
            groups.clear();
        }
        Group g = null;
 
        g = new Group();
        g.setGroupId(1);
        g.setNameGroup("������");
        g.setCurator("������ ����������");
        g.setSpeciality("�������� ������� �� ���������");
        groups.add(g);
 
        g = new Group();
        g.setGroupId(2);
        g.setNameGroup("������");
        g.setCurator("��������� ��������������");
        g.setSpeciality("�������� ��������� �� �������");
        groups.add(g);
    }
 
    // ����� ������� ��������� ��������� � �������� �� � ���������
    public void loadStudents() {
        if (students == null) {
            // �� ���������� ���������, ������� ������������� ��������� ���� ��������
            students = new TreeSet<Student>();
        } else {
            students.clear();
        }
 
        Student s = null;
        Calendar c = Calendar.getInstance();
 
        // ������ ������
        s = new Student();
        s.setStudentId(1);
        s.setFirstName("����");
        s.setPatronymic("���������");
        s.setSurName("��������");
        s.setSex('�');
        c.set(1990, 3, 20);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);
 
        s = new Student();
        s.setStudentId(2);
        s.setFirstName("�������");
        s.setPatronymic("���������");
        s.setSurName("��������");
        s.setSex('�');
        c.set(1990, 6, 10);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);
 
        // ������ ������
        s = new Student();
        s.setStudentId(3);
        s.setFirstName("����");
        s.setPatronymic("����������");
        s.setSurName("������");
        s.setSex('�');
        c.set(1991, 3, 12);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);
 
        s = new Student();
        s.setStudentId(4);
        s.setFirstName("��������");
        s.setPatronymic("���������");
        s.setSurName("��������");
        s.setSex('�');
        c.set(1991, 7, 19);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);
    }
 
    // �������� ������ �����
    public List<Group> getGroups() {
        return groups;
    }
 
    // �������� ������ ���� ���������
    public Collection<Student> getAllStudents() {
        return students;
    }
 
    // �������� ������ ��������� ��� ������������ ������
    public Collection<Student> getStudentsFromGroup(Group group, int year) {
        Collection<Student> l = new TreeSet<Student>();
        for (Student si : students) {
            if (si.getGroupId() == group.getGroupId() && si.getEducationYear() == year) {
                l.add(si);
            }
        }
        return l;
    }
 
    // ��������� ��������� �� ����� ������ � ����� ����� �������� � ������ ������ � ������ ����� ��������
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
        for (Student si : students) {
            if (si.getGroupId() == oldGroup.getGroupId() && si.getEducationYear() == oldYear) {
                si.setGroupId(newGroup.getGroupId());
                si.setEducationYear(newYear);
            }
        }
    }
 
    // ������� ���� ��������� �� ������������ ������
    public void removeStudentsFromGroup(Group group, int year) {
        // �� �������� ����� ������ ��������� ��� ���, ���� �� ����� �������.
        // �������� �� ����� ���������� �������. ����� ���� �� ������������������
        // ����� ���������� �����, �� �� ������� ����������� � ��������� ����� �������
        // ����� �� �� ������ ���� ����� ����
        Collection<Student> tmp = new TreeSet<Student>();
        for (Student si : students) {
            if (si.getGroupId() != group.getGroupId() || si.getEducationYear() != year) {
                tmp.add(si);
            }
        }
        students = tmp;
    }
 
    // �������� ��������
    public void insertStudent(Student student) {
        // ������ ��������� ������ � ���������
        students.add(student);
    }
 
    // �������� ������ � ��������
    public void updateStudent(Student student) {
        // ���� ����� ������� �������� (�� ��� ��) � �������� ����
        Student updStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // ��� ���� ������� - ���������� ��� � ���������� ����
                updStudent = si;
                break;
            }
        }
        updStudent.setFirstName(student.getFirstName());
        updStudent.setPatronymic(student.getPatronymic());
        updStudent.setSurName(student.getSurName());
        updStudent.setSex(student.getSex());
        updStudent.setDateOfBirth(student.getDateOfBirth());
        updStudent.setGroupId(student.getGroupId());
        updStudent.setEducationYear(student.getEducationYear());
    }
 
    // ������� ��������
    public void deleteStudent(Student student) {
        // ���� ����� ������� �������� (�� ��� ��) � �������
        Student delStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // ��� ���� ������� - ���������� ��� � ���������� ����
                delStudent = si;
                break;
            }
        }
        students.remove(delStudent);
    }
 
    // ���� ��� ��������� ��� �������� ���������
    // ����� ����� ��������� ���� ������������ IDE - �������� NetBeans.
    // ����� �� ��������� ������ ���� �������, ��� ������ �������� ������
    public static void printString(Object s) {
        //System.out.println(s.toString());
        try {
            System.out.println(new String(s.toString().getBytes("windows-1251"), "windows-1251"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }
 
    public static void printString() {
        System.out.println();
    }
}