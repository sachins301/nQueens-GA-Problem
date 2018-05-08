import java.util.*;

public class GeneticAlgorithm{
    public static Random rand = new Random();

    public static ArrayList<Integer> generateGenes(int n){
        ArrayList<Integer> gene = new ArrayList<Integer>(n);
        
        for(int i=0;i<n;i++){
            gene.add(rand.nextInt(n));
        }
        return gene;
    }

    //public static int[] generateGenes(){

//    }

    public static void main(String [] args){
        System.out.println("Enter the number of queens");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        //Calling constructor to set the number of queens

        //System.out.println("Enter the type of crossover(1 or 2)");
        //int crossPoint = sc.nextInt();

        System.out.println("Enter the population size");
        int popSize = sc.nextInt();
        Chromosome[] chr = new Chromosome[popSize];
        ArrayList<Chromosome> chromosome = new ArrayList<Chromosome>(popSize);
        
        int totalScore = 0;
        float totalMerit = 0;
        ArrayList<Integer> gene = new ArrayList<Integer>(num);
        //int[] genes = new int[4];
        for(int i=0; i<popSize; i++){
            chr[i] = new Chromosome(num);
            chromosome.add(chr[i]);
            //chromosome.get(i).display();
            gene = generateGenes(num);
            //System.out.println(gene);
            chromosome.get(i).setGenes(gene);
            totalScore += chromosome.get(i).score;
        }

        for(int i=0; i<popSize; i++){
            chromosome.get(i).setMerit(totalScore);
            totalMerit += chromosome.get(i).merit;
        }

        for(int i=0; i<popSize; i++){
            chromosome.get(i).setRelativeMerit(totalMerit);
            chromosome.get(i).display(); 
            System.out.println();        
        }

        System.out.println("Total Score:"+totalScore);
        System.out.println("...................................................................................................");

         //Reproduction starts here--->

        Operator operator=new Operator(chromosome,num);

        //operator.rouletteWheel();                   //Testing rouletteWheel

        System.out.println("Enter the no of cycles required");
        int cycles=sc.nextInt();



        for(int i=0;i<cycles;i++){

            int opSelector; //operater selecter (crossover or mutation)
            opSelector=rand.nextInt(100);
           // if(opSelector<80){
                //perform crossover
                
                operator.crossover(); //pass the parents to Operator
            //}
            //else{
                //perform mutation
            //}


            //printing each generation
        
         }
    }
}


