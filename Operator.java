import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Operator {
    Chromosome[] chromosome;
    public static Random rand=new Random();


    public Operator(Chromosome[] chromosome){
        this.chromosome=chromosome;

    }

    public Chromosome[] crossOver(){

        Arrays.sort(chromosome,new Sortbyscore());  //sort the parents according to relative merit

        for(int i=1;i<chromosome.length;i++){
            reproduce(i-1,i);
        }

        return chromosome;  //need edits just for reference
    }

    public void mutation(){

    }

    public void reproduce(int p1, int p2){

        int temp1;
        int temp2;

        temp1=chromosome[p1].genes[0];
        temp2=chromosome[p1].genes[1];
        chromosome[p1].setGenesByPos(chromosome[p2].genes[0],0);
        chromosome[p1].setGenesByPos(chromosome[p2].genes[1],1);
        chromosome[p2].setGenesByPos(temp1,0);
        chromosome[p2].setGenesByPos(temp2,1);

        chromosome[p1].reCalculate();
        chromosome[p2].reCalculate();
        return;

    }

    public void rouletteWheel(){

        int p[]=new int[chromosome.length+1]; //popsize+1 0tototalscore
        p[0]=chromosome[0].score;
        for(int i=1;i<chromosome.length;i++){
            p[i]=p[i-1]+chromosome[i-1].score;
        }

        int r=rand.nextInt(p[chromosome.length-1]);



    }

    public void selection(int[] p, int r)

}

class Sortbyscore implements Comparator<Chromosome>
{
    // Used for sorting in ascending order of
    // relative merit
    public int compare(Chromosome a,Chromosome b)
    {
        return (int)(Math.abs(a.getRelativeMerit()) - Math.abs(b.getRelativeMerit()));
    }
}

