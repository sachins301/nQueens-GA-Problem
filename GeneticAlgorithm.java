import java.util.*;

public class GeneticAlgorithm {

    public static Random rand=new Random();

    public static int[] generateGenes(){

        //Generates random numbers from 0 to 3 for gene values

        int[] genes=new int[4];

        for(int i=0;i<4;i++){
            genes[i]=rand.nextInt(4);
        }
        return genes;
    }

    public static void main(String []args){

        System.out.println("Enter the initial population size:");
        Scanner sc= new Scanner(System.in);
        int popSize=sc.nextInt();           //user defined population size
        Chromosome[] chromosome=new Chromosome[popSize];
        int totalScore=0;
        float totalMerit=0;

        int[] genes=new int[4];
        for(int i=0;i<popSize;i++){

            chromosome[i]=new Chromosome(); //this avoids null pointer exception

            genes=generateGenes();          //generates random genes

            chromosome[i].setGenes(genes);

            totalScore+=chromosome[i].score;
        }


        for(int i=0;i<popSize;i++){

            chromosome[i].setMerit(totalScore);
            totalMerit+=chromosome[i].merit;
        }

        for(int i=0;i<popSize;i++){

            chromosome[i].setRelativeMerit(totalMerit);

            chromosome[i].display();
            System.out.println();
        }

        System.out.println("Total Score:"+totalScore);

        //Reproduction starts here--->

        Operator operator=new Operator(chromosome);

        operator.rouletteWheel();                   //Testing rouletteWheel

        System.out.println("Enter the no of cycles required");
        int cycles=sc.nextInt();



        for(int i=0;i<cycles;i++){

            int opSelector; //operater selecter (crossover or mutation)
            opSelector=rand.nextInt(100);
           // if(opSelector<80){
                //perform crossover
                chromosome=operator.crossover(); //pass the parents to Operator
            //}
            //else{
                //perform mutation
            //}


            //printing each generation
                
            // System.out.println("Generation "+(i+1));
            // for(int j=0;j<popSize;j++){

            //     chromosome[j].display();
            //     System.out.println();
            // }
        }


    }
}

