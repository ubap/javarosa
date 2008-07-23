package org.javarosa.core.model.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.javarosa.core.model.QuestionDef;
import org.javarosa.core.services.storage.utilities.Externalizable;
import org.javarosa.core.services.storage.utilities.UnavailableExternalizerException;

public class Selection implements Externalizable {
	public int index;
	public QuestionDef question; //cannot hold reference directly to selectItems, as it is wiped out and rebuilt after every locale change
	
	/**
	 * Note that this constructor should only be used for serialization/deserialization as 
	 * selection is immutable
	 */
	public Selection() {
		
	}
	
	public Selection (int index, QuestionDef question) {
		this.index = index;
		this.question = question;
	}
	
	public String getText () {
		return (String)question.getSelectItems().keyAt(index);
	}
	
	public String getValue () {
		return (String)question.getSelectItems().elementAt(index);
	}
	/* (non-Javadoc)
	 * @see org.javarosa.core.services.storage.utilities.Externalizable#readExternal(java.io.DataInputStream)
	 */
	public void readExternal(DataInputStream in) throws IOException,
			InstantiationException, IllegalAccessException,
			UnavailableExternalizerException {
		index = in.readInt();
		question = new QuestionDef();
		question.readExternal(in);
	}

	/* (non-Javadoc)
	 * @see org.javarosa.core.services.storage.utilities.Externalizable#writeExternal(java.io.DataOutputStream)
	 */
	public void writeExternal(DataOutputStream out) throws IOException {
		out.writeInt(index);
		question.writeExternal(out);
	}
}
