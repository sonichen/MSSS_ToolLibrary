package com.msss;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Excel2Table {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream(new File("list.xlsx"));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            Row headerRow = sheet.getRow(0);
            int departmentIndex = -1;
            int positionIndex = -1;
            int nameIndex = -1;

            for (Cell cell : headerRow) {
                String cellValue = cell.getStringCellValue();
                if ("部门".equals(cellValue)) {
                    departmentIndex = cell.getColumnIndex();
                } else if ("职位".equals(cellValue)) {
                    positionIndex = cell.getColumnIndex();
                } else if ("姓名".equals(cellValue)) {
                    nameIndex = cell.getColumnIndex();
                }
            }

            if (departmentIndex == -1 || positionIndex == -1 || nameIndex == -1) {
                System.err.println("列名未找到！");
                return;
            }

            Map<String, List<Employee>> departmentToEmployees = new HashMap<>();

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                String department = row.getCell(departmentIndex).getStringCellValue();
                String position = row.getCell(positionIndex).getStringCellValue();
                String name = row.getCell(nameIndex).getStringCellValue();

                Employee employee = new Employee(department, position, name);
                departmentToEmployees.computeIfAbsent(department, k -> new ArrayList<>()).add(employee);
            }

            List<String> departmentNames = new ArrayList<>(departmentToEmployees.keySet());

            departmentNames.sort((d1, d2) -> {
                List<String> customOrder = List.of("主席团", "外联部","新媒体部", "组织部", "IT部");
                int index1 = customOrder.indexOf(d1);
                int index2 = customOrder.indexOf(d2);
                if (index1 == -1) index1 = customOrder.size();
                if (index2 == -1) index2 = customOrder.size();
                return Integer.compare(index1, index2);
            });

            System.out.println("<table>");
            System.out.println("<thead>");
            System.out.println("  <tr>");
            System.out.println("    <th>部门</th>");
            System.out.println("    <th>姓名</th>");
            System.out.println("    <th>职位</th>");
            System.out.println("  </tr>");
            System.out.println("</thead>");
            System.out.println("<tbody>");
            int clubCounter = 1; // 初始化俱乐部计数器

             for (String department : departmentNames) {
                System.out.println("  <tr>");

                // 检查部门是否是主席团
                if ("主席团".equals(department)) {
                    List<Employee> employees = departmentToEmployees.get(department);
                    Employee president = null;
                    List<Employee> otherMembers = new ArrayList<>();

                    // 分离主席和其他成员
                    for (Employee employee : employees) {
                        if ("主席".equals(employee.getPosition())) {
                            president = employee;
                        } else {
                            otherMembers.add(employee);
                        }
                    }

                    // 输出主席
                    if (president != null) {

                        System.out.println("<td rowspan=\"4\">主席团</td>");
                        System.out.println("    <td>" + president.getName() + "</td>");
                        System.out.println("    <td>" + president.getPosition() + "</td>");
                        System.out.println("  </tr>");
                    }

                    // 输出其他成员
                    for (Employee member : otherMembers) {
                        System.out.println("  <tr>");
                        System.out.println("    <td>" + member.getName() + "</td>");
                        System.out.println("    <td>" + member.getPosition() + "</td>");
                        System.out.println("  </tr>");
                    }
                    System.out.println("  <tr>");
                    System.out.println(" <td colspan=\"3\">常设部门（4个）</td>");
                    System.out.println("  </tr>");
                } else {
                    // 非主席团部门的处理逻辑
                    if("外联部".equals(department)||"组织部".equals(department)||"新媒体部".equals(department)||"IT部".equals(department)){
                        System.out.println("    <td rowspan=\"" + departmentToEmployees.get(department).size() + "\">" + department + "</td>");
                    }else{
                        System.out.println("    <td rowspan=\"" + departmentToEmployees.get(department).size() + "\">" + clubCounter + ". " + department + "</td>");
                    }

                    List<Employee> employees = departmentToEmployees.get(department);

                    // 自定义排序，将部长排在第一个，副部长（如果有的话）排在第二个
                    employees.sort((e1, e2) -> {
                        if (e1.getPosition().equals("部长")) {
                            return -1; // e1在前
                        } else if (e2.getPosition().equals("部长")) {
                            return 1; // e2在前
                        } else if (e1.getPosition().equals("副部长")) {
                            return -1; // e1在前
                        } else if (e2.getPosition().equals("副部长")) {
                            return 1; // e2在前
                        } else {
                            return e1.getName().compareTo(e2.getName()); // 按姓名排序
                        }
                    });

                    for (int i = 0; i < employees.size(); i++) {
                        if (i > 0) {
                            System.out.println("  <tr>");
                        }
                        Employee employee = employees.get(i);
                        System.out.println("    <td>" + employee.getName() + "</td>");
                        System.out.println("    <td>" + employee.getPosition() + "</td>");
                        System.out.println("  </tr>");
                    }
                    if("IT部".equals(department)){
                        System.out.println("  <tr>");
                        System.out.println(" <td colspan=\"3\">俱乐部（18个）\n</td>");
                        System.out.println("  </tr>");
                    }
                    if(!"主席团".equals(department)&&!"外联部".equals(department)&&!"组织部".equals(department)&&!"新媒体部".equals(department)&&!"IT部".equals(department)){
                            clubCounter++;
                    }
                }
            }



            System.out.println("</tbody>");
            System.out.println("</table>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Employee {
    private String department;
    private String position;
    private String name;

    public Employee(String department, String position, String name) {
        this.department = department;
        this.position = position;
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
