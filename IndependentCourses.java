//given input uses BFS to build a digraph representing the prerequisite structure of this set of courses. It checks whether it's a DAG.
//If it is a DAG, the program will then read from a third input, a list of pairs of courses, each on a line. It determines for each pair whether they're independent, whether they can be taken in either order.

import algs32.BST;
import algs42.Digraph;
import algs42.BreadthFirstDirectedPaths;
import stdlib.*;
public class IndependentCourses {
	public static void main(String[] args) {
		algs32.BST<String, Integer> courses = new BST<String, Integer>();
		StdIn.fromFile("data/a8courses.txt");
		String[] text = StdIn.readAllStrings();
		for (int i=0; i<text.length; i++) {
			courses.put(text[i], i);
			System.out.println(text[i]);}
		
		algs42.Digraph courseGraph = new Digraph(text.length);
		StdIn.fromFile("data/a8prereqs.txt");
		while(StdIn.hasNextLine()) {
			String row = StdIn.readLine();
			String [] courseConnects = row.split("\\s+");
			courseGraph.addEdge(courses.get(courseConnects[0]), courses.get(courseConnects[1]));
		}
		System.out.print("\n");
		System.out.println("Course Pair Independence: \n");
		StdIn.fromFile("data/a8independence.txt");
		while(StdIn.hasNextLine()) {
			String line = StdIn.readLine();
			String [] IndCourses = line.split("\\s+");
			algs42.BreadthFirstDirectedPaths courseCycle = new BreadthFirstDirectedPaths(courseGraph, courses.get(IndCourses[0]));
			algs42.BreadthFirstDirectedPaths courseCycle1 = new BreadthFirstDirectedPaths(courseGraph, courses.get(IndCourses[1]));
			System.out.print(IndCourses[0]+" "+IndCourses[1]+": ");
			if(courseCycle.hasPathTo(courses.get(IndCourses[1])) || courseCycle1.hasPathTo(courses.get(IndCourses[0]))) System.out.println("Not Independent");
			else System.out.println("Independent");
		}
		
	}
}
