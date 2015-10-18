package br.com.vnr.dto;

import java.io.Serializable;

import br.com.vnr.entity.Restaurant;

import com.google.gson.Gson;

public class RankingDTO implements Serializable, Comparable<RankingDTO> {

	private static final long serialVersionUID = 3022399070736129116L;

	private Restaurant restaurant;

	private long qttTotalOfVotes;

	private long qttOfVotes;

	private double percentualOfVotes;

	public RankingDTO() {

	}

	public RankingDTO(Restaurant restaurant, long qttOfVotes,
			long qttTotalOfVotes) {
		this.restaurant = restaurant;
		this.qttOfVotes = qttOfVotes;
		this.qttTotalOfVotes = qttTotalOfVotes;
		this.percentualOfVotes = (Double.valueOf(qttOfVotes) / Double
				.valueOf(qttTotalOfVotes)) * 100;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public long getQttOfVotes() {
		return qttOfVotes;
	}

	public void setQttOfVotes(long qttOfVotes) {
		this.qttOfVotes = qttOfVotes;
	}

	public long getQttTotalOfVotes() {
		return qttTotalOfVotes;
	}

	public void setQttTotalOfVotes(long qttTotalOfVotes) {
		this.qttTotalOfVotes = qttTotalOfVotes;
	}

	public double getPercentualOfVotes() {
		return percentualOfVotes;
	}

	public void setPercentualOfVotes(double percentualOfVotes) {
		this.percentualOfVotes = percentualOfVotes;
	}

	@Override
	public int compareTo(RankingDTO other) {
		if (this.qttOfVotes < other.qttOfVotes) {
			return 1;
		}
		if (this.qttOfVotes > other.qttOfVotes) {
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
