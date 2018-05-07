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

        //Arrays.sort(chromosome,new Sortbyscore());  //sort the parents according to relative merit

        float p[]=rouletteWheel();

        for(int i=0;i<chromosome.length/2;i++){
                   reproduce(p);
        }

        //rouletteWheel();

        return chromosome;  //need edits just for reference
    }



    public void mutation(){

    }



    public void reproduce(float[] p){

    int p1_pos = selection_1(p);
        Chromosome p1=chromosome[p1_pos];
        Chromosome p2=chromosome[selection_2(p,p1_pos)];

    System.out.println(" Parent 1 --> ");
    p1.display();
    System.out.println("\n");
    System.out.println(" Parent 2 --> ");
    p2.display();   
    System.out.println("\n");


        Chromosome child1=p1;
        Chromosome child2=p2;
        Chromosome tmp=p1;
        int split=rand.nextInt(3);

        System.out.println(" split -->"+split);


        for(int i=0;i<=split;i++){
            child1.setGenesByPos(p2.genes[i],i);
            int temp=tmp.genes[i];
            child2.setGenesByPos(temp,i);
                System.out.println(" p1 --> "+tmp);

        }

        child1.reCalculate();
        child2.reCalculate();
    System.out.println(" Child 1 --> ");
    child1.display();
    System.out.println("\n");
    System.out.println(" Child 2 --> ");
    child2.display();
    System.out.println("\n");

        return;

    }



    public float[] rouletteWheel(){

        float p[]=new float[chromosome.length]; //popsize+1 tototalscore
        //p[0]=chromosome[0].score;             //initializing the roulette wheel
        for(int i=0;i<chromosome.length;i++){
            if(i==0)
                p[i] = p[i] + chromosome[i] .relativeMerit;
            else
                p[i] = chromosome[i].relativeMerit + p[i-1];
            //p[i]=p[i]+chromosome[i-1].score;
            System.out.println("Roulette Wheel Part " + i + " -->" + p[i]);
        }

        return p;
        //int k1 = selection_1(p);
        //System.out.println(k1);
        //int k2 = selection_2(p,k1);
        //System.out.println(k2);
    }



    public int selection_1(float[] p){                  // selects one of the parents from the roulette wheel

        int r=rand.nextInt(100);
        System.out.println("Random Number is " + r);
        for(int i=0;i<chromosome.length;i++){
            if(r <= p[i])
            return i;
    }

   return -1;}




   public int selection_2(float[] p,int exclude){         // selects one of the parents from the roulette wheel except the one from selection_1

        int r=rand.nextInt(100);
        System.out.println("Random Number is " + r);
        for(int i=0;i<chromosome.length;i++){
            if(r <= p[i])
                if(i!=exclude){
                    return i;}
                    else
                        selection_2(p,exclude);
                }

   return -1;}

}




// import java.util.Arrays;
// import java.util.Comparator;
// import java.util.Random;

// public class Operator {
//     Chromosome[] chromosome;
//     public static Random rand=new Random();


//     public Operator(Chromosome[] chromosome){
//         this.chromosome=chromosome;

//     }

//     public Chromosome[] crossOver(){

//         //Arrays.sort(chromosome,new Sortbyscore());  //sort the parents according to relative merit

//         float p[]=rouletteWheel();

//         for(int i=1;i<chromosome.length/2;i++){
//                    reproduce(p);
//         }

//         return chromosome;  //need edits just for reference
//     }

//     public void mutation(){

//     }

//     public void reproduce(float[] p){

//         Chromosome p1=selection(p);
//         Chromosome p2=selection(p);

//         Chromosome child1=p1;
//         Chromosome child2=p2;

//         int split=rand.nextInt(3);

//         for(int i=0;i<=split;i++){
//             child1.setGenesByPos(p2.genes[i],i);
//             child2.setGenesByPos(p1.genes[i],i);
//         }
            
        

//         child1.reCalculate();
//         child2.reCalculate();

//         return;

//     }

//     public float[] rouletteWheel(){

//         float p[]=new float[chromosome.length]; //popsize+1 0tototalscore
//         //p[0]=chromosome[0].score;             //initializing the roulette wheel
//         for(int i=0;i<chromosome.length;i++){
//             if(i==0)
//                 p[i] = p[i] + chromosome[i].relativeMerit;
//             else
//                 p[i] = chromosome[i].relativeMerit + p[i-1];
//             //p[i]=p[i]+chromosome[i-1].score;
//             System.out.println("Roulette Wheel Part " + i + " -->" + p[i]);
//         }

//         return p;

//     }

//     public Chromosome selection(float[] p){

//         int r=rand.nextInt(100);
//         //System.out.println(r);
//         //if()

//         return p
//     }

// }

// class Sortbyscore implements Comparator<Chromosome>
// {
//     // Used for sorting in ascending order of
//     // relative merit
//     public int compare(Chromosome a,Chromosome b)
//     {
//         return (int)(Math.abs(a.getRelativeMerit()) - Math.abs(b.getRelativeMerit()));
//     }
// }

