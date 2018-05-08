import java.util.*;

public class Operator {
    int num;
    ArrayList<Chromosome> chromosome = new ArrayList<Chromosome>(num/*Enter a value here*/);
    //Chromosome[] chr = new Chromosome(num);
    public static Random rand = new Random();

    public Operator(ArrayList<Chromosome> chromosome, int n){
        this.chromosome = chromosome;
        num = n;
    }

    public void crossover(int crossPoint){
        ArrayList<Float> p = new ArrayList<Float>(chromosome.size());
        p = rouletteWheel();

        //Introduce crossover rate here??
        for(int i=0; i<chromosome.size()/2;i++){
            reproduce(p,crossPoint);
        }     
    }

    public void reproduce(ArrayList<Float> p,int crossPoint) {
        int p1_pos = selectionOne(p);
        Chromosome p1 = new Chromosome(num);
        Chromosome p2 = new Chromosome(num);
        
        p1 = chromosome.get(p1_pos);
        p2 = chromosome.get(selectionTwo(p,p1_pos));

        System.out.println("Parent 1 -->");
        p1.display();
        System.out.println("Parent 2 -->");
        p2.display();
        System.out.println();

        Chromosome child1 = new Chromosome(num);
        Chromosome child2 = new Chromosome(num);
        //Chromosome child3 = new Chromosome(num);
        //Chromosome child4 = new Chromosome(num)

        // System.out.println("Enter the type of crossover (1 or 2");
        // Scanner sc = new Scanner(System.in);
        // int point = sc.nextInt();

        ArrayList<Integer> p1genes = p1.getGenes();
        ArrayList<Integer> p2genes = p2.getGenes();

        if(crossPoint == 1){

            int split = rand.nextInt(num-1);

            System.out.println("Spliting at pos:--->"+split);

            for(int i=0; i<num; i++){
                if(i<=split){
                    child1.genes.add(p1genes.get(i));
                    child2.genes.add(p2genes.get(i));
                }
                else{
                    child1.genes.add(p2genes.get(i));
                    child2.genes.add(p1genes.get(i));
                }
            }
        }
        else{

            int split1 = rand.nextInt(2)+1;
            int split2;

            while((split2 = rand.nextInt(2)+2) == split1);

            System.out.println("Split1 = "+split1+"\nSplit2 = "+split2);

            for(int i=0; i<num; i++){
                if(i>=split1 && i<split2){
                    child1.genes.add(p1genes.get(i));
                    child2.genes.add(p2genes.get(i));
                }
                else{
                    child1.genes.add(p2genes.get(i));
                    child2.genes.add(p1genes.get(i));
                }
            }
        }

        child1.reCalculate();
        child2.reCalculate();
        
        System.out.println(" Child 1 --> ");
        child1.display();
        System.out.println();
        
        System.out.println(" Child 2 --> ");
        child2.display();
        System.out.println("\n");

            return;
    }

    public ArrayList<Float> rouletteWheel(){

        ArrayList<Float> p = new ArrayList<Float>(chromosome.size());
        for(int i=0; i<chromosome.size(); i++){
            if(i==0)
                p.add(chromosome.get(i).relativeMerit);
            else
                p.add(chromosome.get(i).relativeMerit + p.get(i-1));    
            //p[i]=p[i]+chromosome[i-1].score;
            System.out.println("Roulette Wheel Part" +i+ "-->"+p.get(i));

        }
        
        return p;
        //int k1 = selectionOne(p);
        //System.out.println(k1);
        //int k2 = selectionTwo(p,k1);
        //System.out.println(k2);
        //moonji
        //aliya
    }

    public  int selectionOne( ArrayList<Float> p){
        
        int r = rand.nextInt(100);
        System.out.println("Random number is: "+r);
        for(int i=0; i<chromosome.size(); i++){
            if(r <= p.get(i))
                return i;
        }
        return -1;
    }

    public int selectionTwo(ArrayList<Float> p, int exclude){

        int r = rand.nextInt(100);
        System.out.println("Random Number is: "+r);
        for(int i=0; i<chromosome.size(); i++){
            if(r <= p.get(i))
                if(i != exclude)
                    return i;
                else
                    selectionTwo(p,exclude);
                
        }

    return -1;
    }
}



