package org.sweetlemonade.eclipse.json.preference.colors;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.sweetlemonade.eclipse.json.Container;

public class ColorsContentProvider implements IStructuredContentProvider
{
    @Override
    public void dispose()
    {

    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
    }

    @Override
    public Object[] getElements(Object inputElement)
    {
        return (Object[]) ((Container) inputElement).object;
    }
}