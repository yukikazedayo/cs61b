public class NBody
{
    public static double readRadius(String nofin)
    {
        In in=new In(nofin);
        in.readInt();
        return in.readDouble();
    }
    public  static Planet[] readPlanets(String nofin)
    {
        In in=new In(nofin);
        Planet[] allp=new Planet[in.readInt()];
        in.readDouble();
        for(int i=0;i<allp.length;i++)
        {   double xxPos=in.readDouble();
            double yyPos=in.readDouble();
            double xxVel=in.readDouble();
            double yyVel=in.readDouble();
            double mass=in.readDouble();
            String imgFileName=in.readString();
            allp[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return allp;
    }
    public static void main(String[] args)
    {
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double rad=NBody.readRadius(filename);
        Planet[] allpl=NBody.readPlanets(filename);
        StdDraw.setScale(-rad,rad);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(int i=0;i<allpl.length;i++)
        {
            allpl[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        for(double tnow=0;tnow<=T;tnow+=dt)
        {
            double[] xForces=new double[allpl.length+5];
            double[] yForces=new double[allpl.length+5];
            for(int i=0;i<allpl.length;i++)
            {
                xForces[i]=allpl[i].calcNetForceExertedByX(allpl);
                yForces[i]=allpl[i].calcNetForceExertedByY(allpl);

            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(int i=0;i<allpl.length;i++)
            {
                allpl[i].update(dt,xForces[i],yForces[i]);
                allpl[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", allpl.length);
        StdOut.printf("%.2e\n", rad);
        for (int i = 0; i < allpl.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allpl[i].xxPos, allpl[i].yyPos, allpl[i].xxVel,
                    allpl[i].yyVel, allpl[i].mass, allpl[i].imgFileName);
        }
    }
}
