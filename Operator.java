import java.util.*;

public class Operator {
    int num;
    int popSize;
    ArrayList<Chromosome> chromosome = new ArrayList<Chromosome>(popSize/*Enter a value here*/); //Arraylist with previous genaration
    ArrayList<Chromosome> chrcopy = new ArrayList<Chromosome>(popSize); //Arraylist of children and parents
    //Chromosome[] chr = new Chromosome(num);
    public static Random rand = new Random();

    public Operator(ArrayList<Chromosome> chromosome, int n, int popSize){
        this.popSize = popSize;
        num = n;
        
        //Copying the contents of previous generation
        for(int i=0; i<popSize; i++){
			this.chromosome.add(chromosome.get(i));
			chrcopy.add(chromosome.get(i));
			}
        //Collections.copy(this.chromosome,chromosome);
        //this.chromosome = chromosome;
        //Collections.copy(chrcopy, chromosome); 
    }


    public ArrayList<Chromosome> mutation(float mutationRate){
        int m=(int)mutationRate;
        System.out.println("mutaion rate==="+m);
        for(int i=0;i<m;i++){
            int p_pos=rand.nextInt(popSize); //mutation parent position
            System.out.println("parent==="+p_pos);

            int gene_pos=rand.nextInt(num);
            System.out.println("gene_pos==="+gene_pos);

            int value;
            while((value = rand.nextInt(num)) == chromosome.get(p_pos).genes.get(gene_pos));
            System.out.println("value==="+value);

            System.out.println("Befor Mutation --->");
            chromosome.get(p_pos).display();  

            chromosome.get(p_pos).genes.set(gene_pos,value);
            chromosome.get(p_pos).reCalculate2();

            System.out.println("After Mutation --->");  
            chromosome.get(p_pos).display();
            
            

        }
        //Sorting the new generation on the basis of fitness..here score
        Collections.sort(chrcopy, new Comparator<Chromosome>() {
            @Override public int compare(Chromosome c1, Chromosome c2) {
                return c2.score - c1.score; // Ascending
            }
        });

        return chromosome;
    }


    public ArrayList<Chromosome> crossover(int crossPoint,float crossRate){
        
        int c =(int)crossRate;
        ArrayList<Float> p = new ArrayList<Float>(popSize);
        p = rouletteWheel();

        //Introduce crossover rate here??
        for(int i=0; i<c;i++){
            reproduce(p,crossPoint);
        }  
        
        //chrcopy.sort(Comparator.comparingInt(Chromosome::score));
		
		//Sorting the new generation on the basis of fitness..here score
		Collections.sort(chrcopy, new Comparator<Chromosome>() {
			@Override public int compare(Chromosome c1, Chromosome c2) {
				return c2.score - c1.score; // Ascending
			}
		});
		
		//removing all the chromosomes after the fixed pop.size
		for(int i=popSize;i<chrcopy.size();i++)
			chrcopy.remove(i);
		
		for(int i=0; i<popSize; i++){
			chromosome.set(i,chrcopy.get(i));
		}
		
		//Loop to recalculate all the values
		//for(int i=0; i<popSize; i++)
			//chromosome.get(i).reCalculate();
		//Collections.copy(chromosome,chrcopy);
		return chromosome;    
    }

    public void reproduce(ArrayList<Float> p,int crossPoint) {
        
        int p1_pos = selection(p);
        int p2_pos;

        while((p2_pos=selection(p))==p1_pos);
        
        Chromosome p1 = new Chromosome(num);
        Chromosome p2 = new Chromosome(num);
        
        p1 = chromosome.get(p1_pos);
        p2 = chromosome.get(p2_pos);

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

        ArrayList<Integer> p1genes = new ArrayList<Integer>(num);
        ArrayList<Integer> p2genes = new ArrayList<Integer>(num);
        
        //Adding the genes of the selected parents
        for(int i=0; i<num; i++){
			p1genes.add(p1.genes.get(i));
			p2genes.add(p2.genes.get(i));
		}
					

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
        System.out.println("_____________________________________________________________");
        child2.reCalculate();
        
        System.out.println(" Child 1 --> ");
        child1.display();
        System.out.println();
        
        System.out.println(" Child 2 --> ");
        child2.display();
        System.out.println("\n");

		chrcopy.add(child1);
		chrcopy.add(child2);		
            return;
    }

    public ArrayList<Float> rouletteWheel(){

        //p.add(0);
        ArrayList<Float> p = new ArrayList<Float>(popSize);
        for(int i=0; i<popSize; i++){
            if(i==0)
                p.add(chromosome.get(i).relativeMerit);
            else
                p.add(chromosome.get(i).relativeMerit + p.get(i-1));    
            //p[i]=p[i]+chromosome[i-1].score;
            System.out.println("Roulette Wheel Part " +i+ "-->"+p.get(i));

        }
        
        return p;
        //int k1 = selectionOne(p);
        //System.out.println(k1);
        //int k2 = selectionTwo(p,k1);
        //System.out.println(k2);
        //moonji
        //aliya
    }

    public  int selection( ArrayList<Float> p){
        
        int r = rand.nextInt(100);
        System.out.println("Random number is: "+r);
        for(int i=0; i<popSize; i++){
            if(r <= p.get(i)){
                System.out.println("selection--->"+i);
                return i;
            }
        }
        return -1;
    }

    /*// public int selectionTwo(ArrayList<Float> p, int exclude){

    //     int return_val=0;
    //     int r = rand.nextInt(100);
    //     System.out.println("Random Number is: "+r);
    //     for(int i=0; i<chromosome.size(); i++){
    //         if(r <= p.get(i)){
    //             if(i!= exclude){
    //                 System.out.println("selection--->"+i);
    //                 return i;
    //             }
    //             else{
    //                 System.out.println("recurssion"+i);
    //                 return_val=selectionTwo(p,exclude);
    //             }
    //         }
    //     }

    //     return return_val;
    // }*/
}



