import java.util.*;
public class GeneticAlgorithm{


    public static Random rand = new Random();

    public static ArrayList<Integer> generateGenes(int n){
        ArrayList<Integer> gene = new ArrayList<Integer>(n);
        
        //Loop to randomly add gene value for a chromosome
        for(int i=0;i<n;i++){
            gene.add(rand.nextInt(n));
        }
        return gene;
    }

    //public static int[] generateGenes(){

//    }

    public static void main(String [] args) {
        System.out.println("Enter the number of queens");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        //Calling constructor to set the number of queens

        

        System.out.println("Enter the population size");
        int popSize = sc.nextInt();
        
        System.out.println("Enter the type of crossover(1 or 2)");
        int crossPoint = sc.nextInt();

        System.out.println("Enter the crossover rate(0.3 to 0.4)");
        float crossRate = sc.nextFloat();
        crossRate *= popSize;

        System.out.println("Enter the mutation rate(0.01 to 0.03)");
        float mutationRate = sc.nextFloat();
        mutationRate *= popSize;
        
        Chromosome[] chr = new Chromosome[popSize];
        ArrayList<Chromosome> chromosome = new ArrayList<Chromosome>(popSize);
        
        //to check for convergence
        int change=0;
        int prevScore=0;
        
        int totalScore = 0;
        float totalMerit = 0;
        ArrayList<Integer> gene = new ArrayList<Integer>(num);
        //int[] genes = new int[4];
        
        //Loop to get totalscore of 0th generation
        //Loop to generate genes for chromosome..calling generateGenes()
        for(int i=0; i<popSize; i++){
            chr[i] = new Chromosome(num);
            chromosome.add(chr[i]);
            //chromosome.get(i).display();
            gene = generateGenes(num);
            //System.out.println(gene);
            chromosome.get(i).setGenes(gene);
            totalScore += chromosome.get(i).score;
        }

        prevScore = totalScore;
        
        //Loop to get totalMerit of 0th generation
        for(int i=0; i<popSize; i++){
            chromosome.get(i).setMerit(totalScore);
            totalMerit += chromosome.get(i).merit;
        }

        System.out.println("\t\t\t\tGeneration: 0");
        System.out.println("...................................................................................................");

        //Loop to get Relativemerit and display 0th generation
        for(int i=0; i<popSize; i++){
            chromosome.get(i).setRelativeMerit(totalMerit);
            chromosome.get(i).display(); 
            System.out.println();        
        }

        System.out.println("Total Score:"+totalScore);
        System.out.println("...................................................................................................");

         //Reproduction starts here--->

        //operator.rouletteWheel();                   //Testing rouletteWheel






        

        System.out.println("Enter the no. of generations required");
        int cycles=sc.nextInt();
		
        int[] avgFit=new int[cycles];
        int[] tenFit=new int[cycles];
        int[] max=new int[cycles];

		//Loop for next generations
        for(int i=0;i<cycles;i++){

            int opSelector; //operater selecter (crossover or mutation)
            opSelector=rand.nextInt(100);
           // if(opSelector<80){
                //perform crossover
                //System.out.println("\t\t\t\tGeneration: "+(i+1));
                //System.out.println("...................................................................................................");

                Operator operator = new Operator(chromosome,num,popSize);
                
                chromosome = operator.mutation(mutationRate);
                chromosome = operator.crossover(crossPoint, crossRate); //pass the parents to Operator
               
                
                for(int j=0; j<popSize; j++)
					chromosome.get(j).reCalculate2();
                
                totalScore=0;
                for(int j=0; j<popSize; j++){
					totalScore += chromosome.get(j).score;
				}
                
                totalMerit=0;
                for(int j=0; j<popSize; j++){
					chromosome.get(j).setMerit(totalScore);
					totalMerit += chromosome.get(j).merit;
				}
				
				System.out.println("\t\t\t\tGeneration: "+(i+1));
				System.out.println("...................................................................................................");

				for(int j=0; j<popSize; j++){
					chromosome.get(j).setRelativeMerit(totalMerit);
					chromosome.get(j).display(); 
					System.out.println();        
			    }
			    System.out.println("Total Score:"+totalScore);
			    


                //GRAPH STARTS HERE

                avgFit[i]=totalScore/cycles;
                

                int tenSum=0;
                for(int a=0;a<10;a++){
                    tenSum+=chromosome.get(a).score;
                }
                tenFit[i]=tenSum/10;
                

                max[i]=(chromosome.get(0).score);


                //GRAPH ENDS


			    if(totalScore == prevScore)
					change++;
				else
					change=0;
				
				if(change > 10){
					System.out.println("Achieved convergence in "+ (i-9)+" generations");
					System.out.println("Maximum score(fitness): "+ chromosome.get(0).score);
					break;
				}
	
				prevScore = totalScore;	
            
        
         }


           // XYLineChart_AWT.Draw("Genetic Graph","generations" , "Fitness", "", avgFit, tenFit, max,cycles);



    }


}


