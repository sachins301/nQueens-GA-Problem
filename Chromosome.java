public class Chromosome {
    int genes[]=new int[4];
    int score;

    int distance[]=new int[6];  //distance between the genes

    int weight[]=new int[6];    //if dist<3 weight=-10 else weight=10

    float merit;

    float relativeMerit;

    public Chromosome(){        //initialization of chromosome to avoid null point exception
        genes[0]=0;
        genes[1]=0;
        genes[2]=0;
        genes[3]=0;
        setDistance();
        setWeight();
        setScore();
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
        setDistance();
        setWeight();
        setScore();

    }

    public void setGenesByPos(int gene,int position){
        this.genes[position]=gene;
    }

    public void reCalculate(){
        setDistance();
        setWeight();
        setScore();
    }

    public int[] getGenes() {
        return genes;
    }

    public void display(){

        for(int i=0;i<4;i++)
            System.out.print(genes[i]+"\t");
        System.out.print("\t\tScore:"+score+"\t\tMerit:"+merit+"\t\t\tRelative Merit:"+relativeMerit);
    }

    public void setDistance(){
        this.distance[0]=1+Math.abs(genes[1]-genes[0]); //d1d2
        this.distance[1]=2+Math.abs(genes[2]-genes[0]); //d1d3
        this.distance[2]=3+Math.abs(genes[3]-genes[0]); //d1d4
        this.distance[3]=1+Math.abs(genes[2]-genes[1]); //d2d3
        this.distance[4]=2+Math.abs(genes[3]-genes[1]); //d2d4
        this.distance[5]=1+Math.abs(genes[3]-genes[2]); //d3d4
    }

    public void setWeight(){

        for(int i=0;i<6;i++){
            if(distance[i]<3)
                weight[i]=10;
            else
                weight[i]=20;
        }

    }

    public void setScore(){

        this.score=0;

        for(int i=0;i<6;i++){
            this.score+=distance[i]*weight[i];
        }

    }

    public void setMerit(int totalScore) {
        this.merit =/*Math.round*/((float)totalScore/score);
    }

    public float getMerit() {
        return merit;
    }

    public void setRelativeMerit(float totalMerit) {
        this.relativeMerit = merit*100/totalMerit;
    }

    public float getRelativeMerit() {
        return relativeMerit;
    }
}
