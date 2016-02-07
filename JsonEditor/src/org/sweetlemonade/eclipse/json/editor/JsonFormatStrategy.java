package org.sweetlemonade.eclipse.json.editor;

import java.util.LinkedList;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.formatter.ContextBasedFormattingStrategy;
import org.eclipse.jface.text.formatter.FormattingContextProperties;
import org.eclipse.jface.text.formatter.IFormattingContext;
import org.sweetlemonade.eclipse.json.model.JsonElement;
import org.sweetlemonade.eclipse.json.model.JsonParserMy;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonFormatStrategy extends ContextBasedFormattingStrategy
{
    private final LinkedList<IDocument> mDocuments = new LinkedList<>();
    private final JsonEditor mEditor;
    private JsonElement mElement;
    private JsonFormatter mFormatter;

    public JsonFormatStrategy(JsonEditor editor)
    {
        mEditor = editor;
    }

    @Override
    public void formatterStarts(IFormattingContext context)
    {
        super.formatterStarts(context);

        final IDocument document = (IDocument) context.getProperty(FormattingContextProperties.CONTEXT_MEDIUM);

        mElement = new JsonParserMy(document).parse(); //TODO do it right sometime...
        mFormatter = new JsonFormatter(mElement, mEditor.getMergedPreferenceStore());

        mDocuments.addLast(document);
    }

    @Override
    public void format()
    {
        super.format();

        if (mElement == null)
        {
            return;
        }

        final IDocument document = mDocuments.removeFirst();

        final String format = mFormatter.format(document.getLength());

        document.set(format);

        mEditor.doSaveAfterFormat();
    }

    @Override
    public void formatterStops()
    {
        super.formatterStops();

        mDocuments.clear();
    }
}
