package service;

import java.util.HashMap;
import java.util.Map;

import eo.Site;

public class GeoMapService {

	Double reach=100.0;
	Map<Site, Location> map=new HashMap<Site, Location>();

	public GeoMapService() {
		addSite(Site.A,new Location(1,1));
		addSite(Site.B,new Location(2,2));
		addSite(Site.C,new Location(3,3));
		addSite(Site.D,new Location(4,4));
	}

	public Double getDistance(Site site_from, Site site_to) {
		double distance = -1;
		try {
			distance=map.get(site_from).getDistance(map.get(site_to));
		}catch(Exception e) {
			System.out.println("可能没有这个地点，总之出错了："+e.getMessage());
		}
		
		return distance;
	};

	public Boolean isReachable(Site site_from, Site site_to) {
		return getDistance(site_from, site_to) <= reach;
	}

	private void addSite(Site a, Location location) {
		map.put(a, location);
	}

	private void setReach(Double reach) {
		this.reach = reach;
	}

	class Location {
		double x;
		double y;

		public Location(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public double getDistance(Location location) {
			return Math.sqrt(
					(x - location.getX()) * (x - location.getX()) + (y - location.getY()) * (y - location.getY()));
		}

	}
}
