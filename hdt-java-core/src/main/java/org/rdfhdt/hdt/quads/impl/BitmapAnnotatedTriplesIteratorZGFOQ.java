package org.rdfhdt.hdt.quads.impl;

import java.util.ArrayList;

import org.rdfhdt.hdt.compact.bitmap.Bitmap;
import org.rdfhdt.hdt.enums.ResultEstimationType;
import org.rdfhdt.hdt.exceptions.NotFoundException;
import org.rdfhdt.hdt.exceptions.NotImplementedException;
import org.rdfhdt.hdt.graphs.GraphInformationImpl;
import org.rdfhdt.hdt.quads.QuadID;
import org.rdfhdt.hdt.triples.TripleID;
import org.rdfhdt.hdt.triples.impl.BitmapTriples;
import org.rdfhdt.hdt.triples.impl.BitmapTriplesIteratorZFOQ;

public class BitmapAnnotatedTriplesIteratorZGFOQ extends BitmapTriplesIteratorZFOQ {
	
	// resolves ?POG, ??OG queries
	
	private ArrayList<Bitmap> bitmapsGraph; // graph bitmaps of all triples
	private int posG; // the graph from the search
	
	public BitmapAnnotatedTriplesIteratorZGFOQ(BitmapTriples triples, QuadID pattern, GraphInformationImpl graphs) {
		super();
		this.triples = triples;
		this.pattern = new QuadID();
		this.returnTriple = new QuadID();
		this.bitmapsGraph = graphs.getBitmaps();
		this.posG = pattern.getGraph() - 1;
		newSearch(pattern);
	}
	/*
	 * "trims" the range from the left and the right
	 */
	public void findRange() {
		super.findRange();
		while(maxIndex >= minIndex && !bitmapsGraph.get((int) getTriplePosition(maxIndex)).access(posG)) {
			maxIndex--;
		}
		
		while(maxIndex >= minIndex && !bitmapsGraph.get((int) getTriplePosition(minIndex)).access(posG)) {
			minIndex++;
		}
	}
	
	/* 
	 * Check if there are more solution
	 */
	@Override
	public boolean hasNext() {
		return posIndex<=maxIndex && maxIndex >= minIndex;
	}
	
	/* 
	 * Get the next solution
	 */
	@Override
	public TripleID next() {
	    long posY = adjIndex.get(posIndex); // get the position of the next occurrence of the predicate in AdjY

	    z = patZ!=0 ? patZ : (int)adjIndex.findListIndex(posIndex)+1; //get the next object (z) as the number of list in adIndex corresponding to posIndex
	    y = patY!=0 ? patY : (int) adjY.get(posY); // get the next predicate (y) as the element in adjY stores in position posY
	    x = (int) adjY.findListIndex(posY)+1; //get the next subject (X) as the number of list in adjY corresponding to posY

	    do {
	    	posIndex++; // increase the position of the next occurrence of the predicate
	    } while(posIndex < maxIndex && !bitmapsGraph.get((int) getNextTriplePosition()).access(posG));

	    updateOutput(); // set the components (subject,predicate,object) of the returned triple
	    return returnTriple; // return the triple as solution
	}
	
	public long getTriplePosition(long index) {
		long ret =0;
		try {
			ret = triples.adjZ.find(adjIndex.get(index),patZ);
		} catch (NotFoundException e) {
		}
				
		return ret;
	}
	
	@Override
	public ResultEstimationType numResultEstimation() {
	    return ResultEstimationType.UP_TO;
	}
	
	@Override
	public long getPreviousTriplePosition() {
		throw new NotImplementedException();
	}
	
	@Override
	public boolean hasPrevious() {
		throw new NotImplementedException();
	}
	
	@Override
	public TripleID previous() {
		throw new NotImplementedException();
	}
	
	@Override
	public void goTo(long pos) {
		throw new NotImplementedException();
	}
}
