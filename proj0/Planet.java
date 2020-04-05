public class Planet
{
	public double xxPos,yyPos,xxVel,yyVel,mass;
	public String imgFileName;
	public Planet(double xP,double yP,double xV,
				  double yV,double m,String img)
	{
		xxPos=xP;yyPos=yP;xxVel=xV;yyVel=yV;
		mass=m;imgFileName=img;
	}
	public Planet(Planet p)
	{
		this.xxPos=p.xxPos;
		this.yyPos=p.yyPos;
		this.xxVel=p.xxVel;
		this.yyVel=p.yyVel;
		this.mass=p.mass;
		this.imgFileName=p.imgFileName;
	}
	public double calcDistance(Planet temp)
	{
		double ans=0;
		ans=(this.xxPos-temp.xxPos)*(this.xxPos-temp.xxPos)+(this.yyPos-temp.yyPos)*(this.yyPos-temp.yyPos);
		ans=Math.sqrt(ans);
		return ans;
	}
	public double calcForceExertedBy(Planet temp)
	{
		double dis=this.calcDistance(temp);
		double ans=6.67*10e-12*this.mass*temp.mass/dis/dis;
		return ans;
	}
	public double calcForceExertedByX(Planet temp)
	{
		double dis=this.calcDistance(temp);
		double tot=this.calcForceExertedBy(temp);
		return (temp.xxPos-this.xxPos)/dis*tot;
	}
	public double calcForceExertedByY(Planet temp)
	{
		double dis=this.calcDistance(temp);
		double tot=this.calcForceExertedBy(temp);
		return (temp.yyPos-this.yyPos)/dis*tot;
	}
	public boolean equals(Planet temp)
	{
		if(this==temp)return true;
		return false;
	}
	public double calcNetForceExertedByX(Planet[] allp)
	{
		double ans=0;
		for(int i=0;i<allp.length;i++)
		{	if(!this.equals(allp[i]))
			ans+=this.calcForceExertedByX(allp[i]);
		}
		return ans;
	}
	public double calcNetForceExertedByY(Planet[] allp)
	{
		double ans=0;
		for(int i=0;i<allp.length;i++)
		{	if(!this.equals(allp[i]))
			ans+=this.calcForceExertedByY(allp[i]);
		}
		return ans;
	}
	public void update(double dtime,double forcex,double forcey)
	{
		double ax=forcex/this.mass;
		double ay=forcey/this.mass;
		this.xxVel=this.xxVel+dtime*ax;
		this.yyVel=this.yyVel+dtime*ay;
		this.xxPos=this.xxPos+dtime*this.xxVel;
		this.yyPos=this.yyPos+dtime*this.yyVel;
	}
	public void  draw()
	{
		StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	}

}