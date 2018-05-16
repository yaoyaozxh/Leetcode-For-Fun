/**
690. Employee Importance
https://leetcode.com/problems/employee-importance/description/
*/
////////////////////////////////////////////////////////////////
/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/


// Solution 1: BFS
// Time Complexity: O(n)
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> employeesMap = new HashMap<>();
        Queue<Employee> queue = new LinkedList<>();
        for(Employee e : employees) {
            employeesMap.put(e.id, e);
            if(e.id == id) queue.offer(e);
        }
        int sum = 0;
        while(!queue.isEmpty()) {
            Employee e = queue.poll();
            sum += e.importance;
            for(int subordinate : e.subordinates) {
                queue.offer(employeesMap.get(subordinate));
            }
        }
        return sum;
    }
}

// Solution 2: DFS
// Time Complexity: O(n)
// Use Java8 features just for fun! (Bad performance here)
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> employeesMap = employees.stream()
               .collect(Collectors.toMap(e -> e.id, e -> e));
        return getSumImportance(employeesMap, id);
    }

    private int getSumImportance(Map<Integer, Employee> employeesMap, int id) {
        Employee e = employeesMap.get(id);
        return e.subordinates.stream().map(subid -> getSumImportance(employeesMap, subid)).reduce(e.importance, Integer::sum);
    }
}