package karyheap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.io.FileWriter;
/**
 *
 * @author jorienoll
 */
public class KaryHeap {
    private static int size;
    private static int[] kHeap;
    private static int k;

    public static void main(String[] args) throws IOException{
        File textFile = new File("location.txt");
        BufferedReader br = new BufferedReader(new FileReader(textFile));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                String[] separate = line.split(" ");
                if (separate[0] == "IN"){
                    insert(Integer.parseInt(separate[1]));
                }
                else if(separate[0] == "EX"){
                    int min = extractMin(kHeap);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        print();
    }

    public static void print() throws IOException{
	String fileContent = " insert shit here";
        FileWriter fileWriter = new FileWriter(".txt");
        fileWriter.write(fileContent);
        fileWriter.close();
    }

    public KaryHeap(int capacity, int numChild)
    {
        size = 0;
        kHeap = new int[capacity + 1];
        Arrays.fill(kHeap, -1);
    }

    /** Function to  get index parent of i **/
    private static int parent(int i)
    {
        return (i - 1)/k;
    }

    /** Function to get index of k th child of i **/
    private static int kthChild(int i, int j)
    {
        return k * i + j;
    }

    /** Function to delete element at an index **/
    public static int delete(int index)
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        int keyItem = kHeap[index];
        kHeap[index] = kHeap[size - 1];
        size--;
        heapifyDown(index);
        return keyItem;
    }

    /** Function heapifyUp  **/
    private static void heapifyUp(int childInd)
    {
        int tmp = kHeap[childInd];
        while (childInd > 0 && tmp < kHeap[parent(childInd)])
        {
            kHeap[childInd] = kHeap[ parent(childInd) ];
            childInd = parent(childInd);
        }
        kHeap[childInd] = tmp;
    }

    /** Function heapifyDown **/
    private static void heapifyDown(int ind)
    {
        int child;
        int tmp = kHeap[ ind ];
        while (kthChild(ind, 1) < size)
        {
            child = minChild(ind);
            if (kHeap[child] < tmp)
                kHeap[ind] = kHeap[child];
            else
                break;
            ind = child;
        }
        kHeap[ind] = tmp;
    }

    /** Function to get smallest child **/
    private static int minChild(int ind)
    {
        int bestChild = kthChild(ind, 1);
        int d = 2;
        int pos = kthChild(ind, d);
        while ((d <= k) && (pos < size))
        {
            if (kHeap[pos] < kHeap[bestChild])
                bestChild = pos;
            pos = kthChild(ind, d++);
        }
        return bestChild;
    }

    public static void heapify(){
        //if heap holds true
            return;
        //else if the replacement node value <= its parent nodes value then swap them and repeat heapify.
        //swap the replacement node with smallest child node and repeat heapify
    }

        /** Function to insert element */
    public static void insert(int x)
    {
        if (isFull( ) )
            throw new NoSuchElementException("Overflow Exception");
        /** Percolate up **/
        kHeap[size++] = x;
        heapify(size - 1);
    }

    public static int extractMin(int arr[]){
        //store the minimum value
        int minData = kHeap[0];
        //remove the minimum value from array
        delete(0);
        //
        arr[0] = arr[size - 1];
        heapify();

        //export the minimum value
        return minData;
    }
}
