package ${project_package};

import com.sun.star.uno.XComponentContext;
import com.sun.star.lib.uno.helper.Factory;
import com.sun.star.lang.XSingleComponentFactory;
import com.sun.star.registry.XRegistryKey;
import com.sun.star.lib.uno.helper.WeakBase;


public final class ${project_class_name}Impl extends WeakBase
   implements com.sun.star.lang.XServiceInfo,
              com.sun.star.lang.XLocalizable,
              ${project_package}.X${project_class_name}
{
    private final XComponentContext m_xContext;
    private static final String m_implementationName = ${project_class_name}Impl.class.getName();
    private static final String[] m_serviceNames = {
        "${project_package}.${project_class_name}" };

    private com.sun.star.lang.Locale m_locale = new com.sun.star.lang.Locale();

    public ${project_class_name}Impl( XComponentContext context )
    {
        m_xContext = context;
    }

    public static XSingleComponentFactory __getComponentFactory( String sImplementationName ) {
        XSingleComponentFactory xFactory = null;

        if ( sImplementationName.equals( m_implementationName ) )
            xFactory = Factory.createComponentFactory(${project_class_name}Impl.class, m_serviceNames);
        return xFactory;
    }

    public static boolean __writeRegistryServiceInfo( XRegistryKey xRegistryKey ) {
        return Factory.writeRegistryServiceInfo(m_implementationName,
                                                m_serviceNames,
                                                xRegistryKey);
    }

    // com.sun.star.lang.XServiceInfo:
    public String getImplementationName() {
         return m_implementationName;
    }

    public boolean supportsService( String sService ) {
        int len = m_serviceNames.length;

        for( int i=0; i < len; i++) {
            if (sService.equals(m_serviceNames[i]))
                return true;
        }
        return false;
    }

    public String[] getSupportedServiceNames() {
        return m_serviceNames;
    }

    // com.sun.star.lang.XLocalizable:
    public void setLocale(com.sun.star.lang.Locale eLocale)
    {
        m_locale = eLocale;
    }

    public com.sun.star.lang.Locale getLocale()
    {
        return m_locale;
    }

    // ${project_package}.X${project_class_name}:
    public String myFunction(String parameter0)
    {
        // TODO: Exchange the default return implementation for "myFunction" !!!
        // NOTE: Default initialized polymorphic structs can cause problems
        // because of missing default initialization of primitive types of
        // some C++ compilers or different Any initialization in Java and C++
        // polymorphic structs.
        String result = parameter0 + " Some Extra";
        return result;
    }

}
