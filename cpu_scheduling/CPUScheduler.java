package cpu_scheduling;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// Process class to hold process attributes
class Process {
    String id;
    int arrivalTime, burstTime, remainingTime, completionTime, waitingTime, turnaroundTime;
    
    public Process(String id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class CPUScheduler {
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            // Display menu options for the user
            System.out.println("CPU Scheduling Simulator");
            System.out.println("1. First-Come, First-Served (FCFS)");
            System.out.println("2. Shortest-Job-First (SJF)");
            System.out.println("3. Shortest-Remaining-Time (SRT)");
            System.out.println("4. Round Robin (RR)");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            
            if (choice == 5) break;
            
            // Get process details from the user
            List<Process> processes = getProcesses();
            switch (choice) {
                case 1 -> fcfs(processes);
                case 2 -> sjf(processes);
                case 3 -> srt(processes);
                case 4 -> roundRobin(processes);
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
    
    // Method to get process details from the user
    private static List<Process> getProcesses() {
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID, Arrival Time, Burst Time: ");
            String id = scanner.next();
            int at = scanner.nextInt();
            int bt = scanner.nextInt();
            processes.add(new Process(id, at, bt));
        }
        return processes;
    }
    
    // First-Come, First-Served (FCFS) scheduling algorithm
    private static void fcfs(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int time = 0;
        for (Process p : processes) {
            time = Math.max(time, p.arrivalTime);
            time += p.burstTime;
            p.completionTime = time;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
        }
        displayResults(processes, "FCFS");
    }
    
    // Shortest Job First (SJF) scheduling algorithm (Non-Preemptive)
    private static void sjf(List<Process> processes) {
        List<Process> readyQueue = new ArrayList<>();
        int time = 0;
        while (!processes.isEmpty()) {
            for (Process p : processes) {
                if (p.arrivalTime <= time && !readyQueue.contains(p)) {
                    readyQueue.add(p);
                }
            }
            if (readyQueue.isEmpty()) {
                time++;
                continue;
            }
            readyQueue.sort(Comparator.comparingInt(p -> p.burstTime));
            Process current = readyQueue.remove(0);
            time += current.burstTime;
            current.completionTime = time;
            current.turnaroundTime = current.completionTime - current.arrivalTime;
            current.waitingTime = current.turnaroundTime - current.burstTime;
            processes.remove(current);
        }
        displayResults(readyQueue, "SJF");
    }
    
    // Shortest Remaining Time (SRT) scheduling algorithm
// Shortest Remaining Time (SRT) scheduling algorithm
private static void srt(List<Process> processes) {
    AtomicInteger time = new AtomicInteger(0); // Create an AtomicInteger for time
    int completed = 0;
    
    while (completed < processes.size()) {
        // Find the process with the shortest remaining time
        Process current = processes.stream()
            .filter(p -> p.arrivalTime <= time.get() && p.remainingTime > 0) // Use time.get() to access the value
            .min(Comparator.comparingInt(p -> p.remainingTime))
            .orElse(null);

        if (current == null) {
            time.incrementAndGet(); // Increment time by 1 if no process is available
            continue;
        }

        current.remainingTime--; // Decrease the remaining time of the process
        time.incrementAndGet(); // Increment time by 1

        if (current.remainingTime == 0) {
            current.completionTime = time.get(); // Set completion time when process is finished
            current.turnaroundTime = current.completionTime - current.arrivalTime;
            current.waitingTime = current.turnaroundTime - current.burstTime;
            completed++; // Increment completed processes count
        }
    }
    
    // Display results after processing all
    displayResults(processes, "SRT");
}

    
    // Round Robin scheduling algorithm
    private static void roundRobin(List<Process> processes) {
        System.out.print("Enter time quantum: ");
        int quantum = scanner.nextInt();
        Queue<Process> queue = new LinkedList<>(processes);
        int time = 0;
        while (!queue.isEmpty()) {
            Process current = queue.poll();
            if (current.arrivalTime > time) {
                time = current.arrivalTime;
            }
            int execTime = Math.min(quantum, current.remainingTime);
            time += execTime;
            current.remainingTime -= execTime;
            if (current.remainingTime > 0) {
                queue.offer(current);
            } else {
                current.completionTime = time;
                current.turnaroundTime = current.completionTime - current.arrivalTime;
                current.waitingTime = current.turnaroundTime - current.burstTime;
            }
        }
        displayResults(processes, "Round Robin");
    }
    
    private static void displayResults(List<Process> processes, String algoName) {
        System.out.println("\nResults for " + algoName + " Scheduling:");
        System.out.printf("%-10s %-15s %-15s %-15s %-15s %-15s\n", "Process", "Arrival Time", "Burst Time", "Completion Time", "Turnaround Time", "Waiting Time");
        for (Process p : processes) {
            System.out.printf("%-10s %-15d %-15d %-15d %-15d %-15d\n",
                    p.id, p.arrivalTime, p.burstTime, p.completionTime, p.turnaroundTime, p.waitingTime);
        }
    }
}
