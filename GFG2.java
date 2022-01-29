// { Driver Code Starts
//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
import java.math.*;

class Node  
{ 
    int data; 
    Node left, right; 
   
    public Node(int d)  
    { 
        data = d; 
        left = right = null; 
    } 
}

class GFG
{
    static Node buildTree(String str)
    {
        // Corner Case
        if(str.length() == 0 || str.equals('N'))
            return null;
        String[] s = str.split(" ");
        
        Node root = new Node(Integer.parseInt(s[0]));
        Queue <Node> q = new LinkedList<Node>();
        q.add(root);
        
        // Starting from the second element
        int i = 1;
        while(!q.isEmpty() && i < s.length)
        {
              // Get and remove the front of the queue
              Node currNode = q.remove();
        
              // Get the curr node's value from the string
              String currVal = s[i];
        
              // If the left child is not null
              if(!currVal.equals("N")) 
              {
        
                  // Create the left child for the curr node
                  currNode.left = new Node(Integer.parseInt(currVal));
        
                  // Push it to the queue
                  q.add(currNode.left);
              }
        
              // For the right child
              i++;
              if(i >= s.length)
                  break;
              currVal = s[i];
        
              // If the right child is not null
              if(!currVal.equals("N")) 
              {
        
                  // Create the right child for the curr node
                  currNode.right = new Node(Integer.parseInt(currVal));
        
                  // Push it to the queue
                  q.add(currNode.right);
              }
              
              i++;
        }
    
        return root;
    }
    
    public static void main(String args[]) throws IOException {
    
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t>0)
        {
            String s = br.readLine();
            Node root1 = buildTree(s);
            
            s = br.readLine();
            Node root2 = buildTree(s);
            
            Solution T = new Solution();
            List<Integer> ans = T.merge(root1,root2);
            for(int i=0;i<ans.size();i++)
                System.out.print(ans.get(i) + " ");
            System.out.println();
            
            t--;
        }
    }
}
// } Driver Code Ends


//User function Template for Java


/*
class Node  
{ 
    int data; 
    Node left, right; 
   
    public Node(int d)  
    { 
        data = d; 
        left = right = null; 
    } 
}
    
*/
class Solution
{
    static Deque<Node> st1;
    static Deque<Node> st2;
    //Function to return a list of integers denoting the node 
    //values of both the BST in a sorted order.
    public List<Integer> merge(Node root1,Node root2)
    {
        st1=new ArrayDeque<>();
        st2=new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        traverseBothBSTs(root1,root2,result);
        return result;
    }
    
    private void traverseBothBSTs(Node root1, Node root2, List<Integer> result) {
        if(st1.isEmpty()){
            pushLeftTreeToStack(root1, st1);
        }
        if(st2.isEmpty()){
            pushLeftTreeToStack(root2, st2);
        }
        while(!st1.isEmpty() || !st2.isEmpty()){
            Node temp1, temp2;
            temp1 = st1.peek();
            temp2 = st2.peek();
            if (temp1 == null) {
                outputNode(result, temp2, st2);
            } else if (temp2 == null) {
                outputNode(result, temp1, st1);
            } else if (temp1.data < temp2.data) {
                outputNode(result, temp1, st1);
            } else if (temp1.data == temp2.data) {
                outputNode(result, temp1, st1);
                outputNode(result, temp2, st2);
            } else {
                outputNode(result, temp2, st2);
            }
        }
    }


    private static void outputNode(List<Integer> result, Node root, Deque<Node> stack) {
        result.add(root.data);
        stack.pop();
        pushRightTreeToStack(root, stack);
    }
    
    private static void pushLeftTreeToStack(Node root, Deque<Node> stack) {
        if(root!=null) {
            stack.push(root);
            while (root.left != null) {
                stack.push(root.left);
                root = root.left;
            }
        }
    }

    private static void pushRightTreeToStack(Node root,Deque<Node> stack) {
        if (root.right != null) {
            Node right = root.right;
            pushLeftTreeToStack(right, stack);
        }
    }

}
