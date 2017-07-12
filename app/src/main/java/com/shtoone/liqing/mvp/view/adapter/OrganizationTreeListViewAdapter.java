package com.shtoone.liqing.mvp.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shtoone.liqing.R;
import com.shtoone.liqing.mvp.view.treeview.Node;
import com.shtoone.liqing.mvp.view.treeview.TreeHelper;
import com.shtoone.liqing.mvp.view.treeview.TreeListViewAdapter;

import java.util.List;

public class OrganizationTreeListViewAdapter<T> extends TreeListViewAdapter<T> {

    private Context context;

    public OrganizationTreeListViewAdapter(ListView tree, Context context, List<T> list, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        super(tree, context, list, defaultExpandLevel);
        this.context=context;
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_tree_listview_organization_fragment, parent, false);
            holder = new ViewHolder();
            holder.mIcon = (ImageView) convertView
                    .findViewById(R.id.id_item_icon);
            holder.mText = (TextView) convertView
                    .findViewById(R.id.id_item_text);
            holder.mText.setTextColor(context.getResources().getColor(R.color.black));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (node.getIcon() == -1) {
            holder.mIcon.setVisibility(View.INVISIBLE);
        } else {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageResource(node.getIcon());
        }
        holder.mText.setTextColor(context.getResources().getColor(R.color.black));
        holder.mText.setText(node.getName());
        return convertView;
    }


    private class ViewHolder {
        ImageView mIcon;
        TextView mText;
    }

    /**
     * 动态插入节点
     *
     * @param position
     * @param string
     */
    public void addExtraNode(int position, String string) {
        Node node = mVisibleNodes.get(position);
        int indexOf = mAllNodes.indexOf(node);
        // Node
        Node extraNode = new Node("", node.getId(), string);
        extraNode.setParent(node);
        node.getChildren().add(extraNode);
        mAllNodes.add(indexOf + 1, extraNode);

        mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
        notifyDataSetChanged();

    }

}
