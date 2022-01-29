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
    //Function to return a list of integers denoting the node 
    //values of both the BST in a sorted order.
    public List<Integer> merge(Node root1,Node root2)
    {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        traverse(root1,l1);
        traverse(root2,l2);
        if(l1.size()==0)
            return l2;
        else if(l2.size()==0)
            return l1;
        else return mergeLists(l1,l2);
    }
    
    private List<Integer> mergeLists(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new ArrayList<>();
        int l1ctr=0;
        int l2ctr=0;
        /*
        l1.forEach(e-> System.out.print(" "+e));
        System.out.println(l1.size()+" "+l2.size());
        l2.forEach(e-> System.out.print(" "+e));
        */
        while(l1ctr<l1.size() && l2ctr<l2.size()){
            if(l1.get(l1ctr)<l2.get(l2ctr)){
                result.add(l1.get(l1ctr));
                l1ctr++;
            }else if(l1.get(l1ctr)==l2.get(l2ctr)){
                result.add(l1.get(l1ctr));
                result.add(l2.get(l2ctr));
                l1ctr++;
                l2ctr++;
            }else{
                result.add(l2.get(l2ctr));
                l2ctr++;
            }
        }
        if(l1ctr<l1.size()){
            while(l1ctr<l1.size())
                result.add(l1.get(l1ctr++));
        }
        if(l2ctr<l2.size()){
            while(l2ctr<l2.size())
                result.add(l2.get(l2ctr++));
        }
        return result;
    }

    private void traverse(Node root, List<Integer> l) {
        if(root==null)
            return;
        else{
            traverse(root.left,l);
            l.add(root.data);
            traverse(root.right,l);
        }
    }
}
