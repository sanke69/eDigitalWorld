package fr.xs.DigitalWorld.sdk.common.types.map.attributes.others;

public enum ReliefType {
		Lake				( 0, "Lac"),
		Sea					( 0, "Mer"),
		Water				( 0, "Eau"),
		Ground				( 1, "Sol"),
		Forest				( 2, "Foret"),
		Unknown				(-1, "Non défini");

		private final int    value;
		private final String desc;

	    private ReliefType(int _value, String _desc) {
	    	value = _value;
	    	desc  = _desc;
	    }

	    public int getValue() {
	        return this.value;
	    }
	    public String getDescription() {
	        return this.desc;
	    }

	}