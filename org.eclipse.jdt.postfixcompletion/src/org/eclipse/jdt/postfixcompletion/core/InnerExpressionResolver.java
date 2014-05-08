package org.eclipse.jdt.postfixcompletion.core;

import org.eclipse.jdt.internal.corext.template.java.JavaVariable;
import org.eclipse.jface.text.templates.SimpleTemplateVariableResolver;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateVariable;


/**
 * This class is a resolver for the variable <code>inner_expression</code>.
 * <br/>
 * The value of the resolved variable will be the source code of the node which resolves to the <code>inner_expression</code>
 * The type of the resolved variable will be the fully qualified name or the name of the base type of the node which resolves to the <code>inner_expression</code>.
 */
@SuppressWarnings("restriction")
public class InnerExpressionResolver extends SimpleTemplateVariableResolver {
	
	public static final String INNER_EXPRESSION_VAR = "inner_expression"; //$NON-NLS-1$
	
	public InnerExpressionResolver() {
		super(INNER_EXPRESSION_VAR, ""); // TODO Add description
	}
	
	protected String resolve(TemplateContext context) {
		if (!(context instanceof JavaStatementPostfixContext)) 
			return "";
		
		return ((JavaStatementPostfixContext)context).getAffectedStatement();
	}
	
	@Override
	public void resolve(TemplateVariable variable, TemplateContext context) {
		if (context instanceof JavaStatementPostfixContext && variable instanceof JavaVariable) {
			JavaStatementPostfixContext c = (JavaStatementPostfixContext) context;
			JavaVariable jv = (JavaVariable) variable;
			jv.setValue(resolve(context));
			jv.setParamType(c.getInnerExpressionTypeSignature());
			jv.setResolved(true);
			jv.setUnambiguous(true);
			return;
		}
		super.resolve(variable, context);
	}
}
