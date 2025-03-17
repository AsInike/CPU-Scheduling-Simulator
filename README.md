CPU Scheduling Simulator
Project Description
The CPU Scheduling Simulator is a Java-based program that simulates the execution of processes using different CPU scheduling algorithms. The program allows users to input process information (such as arrival time, burst time, etc.) and choose from four scheduling algorithms:

First-Come, First-Served (FCFS)
Shortest-Job-First (SJF)
Shortest-Remaining-Time (SRT)
Round Robin (RR)
The simulator calculates and displays various process metrics, including waiting times, turnaround times, and completion times for each process. It also generates a Gantt chart to visually represent the process execution order. This project is ideal for understanding how each scheduling algorithm impacts the performance of processes in a CPU.

Features
User Input: Allows users to define the number of processes, their arrival times, burst times, and time quantum for Round Robin scheduling.
Multiple Algorithms: Choose from four common CPU scheduling algorithms.
Detailed Output: Displays the results of scheduling with waiting times, turnaround times, and average times for each algorithm.
Interactive Interface: Simple terminal-based menu to select the algorithm and input data.
Requirements
Java 8 or later
A terminal or IDE that supports running Java programs.
Instructions
1. Clone the Repository
Clone the project repository to your local machine using the following command:

bash
Copy
Edit
git clone https://github.com/AsInike/cpu-scheduling-simulator.git
2. Compile the Program
Navigate to the project directory and compile the Java files using the javac command:

bash
Copy
Edit
cd cpu-scheduling-simulator
javac CPUScheduler.java
This will compile the CPUScheduler.java file, generating the CPUScheduler.class file.

3. Run the Program
After compilation, run the program using the java command:

bash
Copy
Edit
java CPUScheduler
You will be prompted to choose a scheduling algorithm from the following options:

First-Come, First-Served (FCFS)
Shortest-Job-First (SJF)
Shortest-Remaining-Time (SRT)
Round Robin (RR)
Exit
4. Input Data
For each scheduling algorithm, you will be asked to enter the number of processes and their respective arrival times and burst times. For Round Robin, you will also need to specify the time quantum.

5. View Results
After running a scheduling algorithm, the program will display:

A Gantt chart showing the execution sequence of processes.
Waiting times and Turnaround times for each process.
Average waiting time and average turnaround time.
Example Output
Example Gantt Chart (FCFS)
yaml
Copy
Edit
Gantt Chart: P1 (0–6) → P2 (6–14) → P3 (14–21) → P4 (21–24)

Waiting Times: P1 = 0, P2 = 5, P3 = 12, P4 = 18

Turnaround Times: P1 = 6, P2 = 13, P3 = 19, P4 = 21

Average Waiting Time: 8.75

Average Turnaround Time: 14.75
Algorithms
1. First-Come, First-Served (FCFS)
Processes are executed in the order they arrive, without preemption.
2. Shortest-Job-First (SJF)
Non-preemptive. The process with the shortest burst time is selected for execution next.
3. Shortest-Remaining-Time (SRT)
Preemptive. The process with the shortest remaining burst time is selected for execution next.
4. Round Robin (RR)
Each process is assigned a fixed time quantum. If a process doesn't finish within the quantum, it is placed back in the queue to wait for its next turn.
License
This project is licensed under the MIT License - see the LICENSE file for details.

