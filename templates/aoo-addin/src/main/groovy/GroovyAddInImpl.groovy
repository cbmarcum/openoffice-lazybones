package ${project_package}

import com.sun.star.uno.XComponentContext
import com.sun.star.lib.uno.helper.Factory
import com.sun.star.lang.XSingleComponentFactory
import com.sun.star.registry.XRegistryKey
import com.sun.star.lib.uno.helper.WeakBase

@groovy.transform.CompileStatic
final class ${project_class_name}Impl extends WeakBase
implements com.sun.star.lang.XServiceInfo,
com.sun.star.lang.XLocalizable,
${project_package}.X${project_class_name} {
    private final XComponentContext m_xContext
    private static final String m_implementationName = ${project_class_name}Impl.class.getName()
    private static final String[] m_serviceNames = ["${project_package}.${project_class_name}"] as String[]

    private com.sun.star.lang.Locale m_locale = new com.sun.star.lang.Locale()

    ${project_class_name}Impl( XComponentContext context ) {
        m_xContext = context
    }

    static XSingleComponentFactory __getComponentFactory( String sImplementationName ) {
        XSingleComponentFactory xFactory = null

        if ( sImplementationName.equals( m_implementationName ) )
        xFactory = Factory.createComponentFactory(${project_class_name}Impl.class, m_serviceNames)
        return xFactory
    }

    static boolean __writeRegistryServiceInfo( XRegistryKey xRegistryKey ) {
        return Factory.writeRegistryServiceInfo(m_implementationName,
            m_serviceNames,
            xRegistryKey)
    }

    // com.sun.star.lang.XServiceInfo:
    String getImplementationName() {
        return m_implementationName
    }

    boolean supportsService( String sService ) {
        int len = m_serviceNames.length

        for( int i=0; i < len; i++) {
            if (sService.equals(m_serviceNames[i]))
            return true
        }
        return false
    }

    String[] getSupportedServiceNames() {
        return m_serviceNames
    }

    // com.sun.star.lang.XLocalizable:
    void setLocale(com.sun.star.lang.Locale eLocale) {
        m_locale = eLocale
    }

    com.sun.star.lang.Locale getLocale() {
        return m_locale
    }

    // ${project_package}.X${project_class_name}:
    String myFunction(String parameter0) {
        String result = parameter0 + " Some Extra"
        return result
    }

}
