package ${project_package}

import com.sun.star.awt.*
import com.sun.star.container.XIndexAccess
import com.sun.star.frame.FrameSearchFlag
import com.sun.star.frame.XDesktop
import com.sun.star.frame.XModel
import com.sun.star.io.IOException
import com.sun.star.lang.*
import com.sun.star.lang.IllegalArgumentException
import com.sun.star.lang.IndexOutOfBoundsException
import com.sun.star.text.XTextDocument
import com.sun.star.text.XTextRange
import com.sun.star.uno.Exception
import com.sun.star.uno.UnoRuntime
import com.sun.star.uno.XComponentContext
import com.sun.star.lib.uno.helper.Factory
import com.sun.star.registry.XRegistryKey
import com.sun.star.lib.uno.helper.WeakBase
import com.sun.star.view.XSelectionSupplier

// used with Groovy UNO Extension
import org.openoffice.guno.UnoExtension

// for command1
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.StringTokenizer
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel

final class ${project_class_name} extends WeakBase
        implements com.sun.star.lang.XServiceInfo,
        com.sun.star.frame.XDispatch,
        com.sun.star.lang.XInitialization,
        com.sun.star.frame.XDispatchProvider {

    private final XComponentContext m_xContext
    private com.sun.star.frame.XFrame m_xFrame
    private static final String m_implementationName = ${project_class_name}.class.getName()
    private static final String[] m_serviceNames = {
            "com.sun.star.frame.ProtocolHandler"}

    ${project_class_name}(XComponentContext context) {
        m_xContext = context;
    }


    static XSingleComponentFactory __getComponentFactory(String sImplementationName) {
        XSingleComponentFactory xFactory = null;

        if (sImplementationName.equals(m_implementationName)) {
            xFactory = Factory.createComponentFactory(${project_class_name}.class, m_serviceNames);
        }
        return xFactory;
    }

    static boolean __writeRegistryServiceInfo(XRegistryKey xRegistryKey) {
        return Factory.writeRegistryServiceInfo(m_implementationName,
                m_serviceNames,
                xRegistryKey)
    }

    // com.sun.star.lang.XServiceInfo:
    String getImplementationName() {
        return m_implementationName
    }

    boolean supportsService(String sService) {
        int len = m_serviceNames.length

        for (int i = 0; i < len; i++) {
            if (sService.equals(m_serviceNames[i])) {
                return true
            }
        }
        return false
    }

    String[] getSupportedServiceNames() {
        return m_serviceNames
    }

    // com.sun.star.frame.XDispatch:
    void dispatch(com.sun.star.util.URL aURL,
                         com.sun.star.beans.PropertyValue[] aArguments) {
        if (aURL.Protocol.compareTo("${project_package}.${project_lowercase_name}:") == 0) {
            if (aURL.Path.compareTo("Command0") == 0) {
                // add your own code here

                /* SAMPLE COMMAND CODE BEGIN */
                com.sun.star.frame.XController xController = m_xFrame.getController()
                XModel xModel = xController.getModel()
                XTextDocument xTextDocument = xModel.guno(XTextDocument.class)
                println("xTextDocument = " + xTextDocument)

                if (xTextDocument == null) {
                    // start a new doc
                    XComponentLoader xCLoader = xComponentContext.componentLoader
                    com.sun.star.beans.PropertyValue [] szEmptyArgs =
                            new com.sun.star.beans.PropertyValue [0]
                    String strDoc = "private:factory/swriter"

                    println("create new text document")

                    com.sun.star.lang.XComponent xComp = null;
                    try {
                        xComp = xCLoader.loadComponentFromURL(
                                strDoc, "_default", 0, szEmptyArgs)
                    } catch (IOException e) {
                        e.printStackTrace()
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace()
                    }

                    // query the new document for the XTextDocument interface
                    xTextDocument = xComp.guno(com.sun.star.text.XTextDocument.class)

                } else {
                    // use this one
                    println("Using current XTextDocument...")
                }
                // create some example data
                com.sun.star.text.XText xText = xTextDocument.getText()

                // get an (empty) XTextRange at the end of the document
                com.sun.star.text.XTextRange xTextRange = xText.getEnd()

                // sets the text of the text range
                xTextRange.setString("This is an example sentence." )

                xText = xTextDocument.getText()
                xTextRange = xText.getEnd()

                try {
                    // set the cursor to the end of text
                    com.sun.star.text.XWordCursor xWordCursor =
                            xTextRange.guno(com.sun.star.text.XWordCursor.class)
                    // backup cursor 2 words
                    xWordCursor.gotoPreviousWord(false)
                    xWordCursor.gotoPreviousWord(false)
                    // select 2nd word from the end.
                    xWordCursor.gotoEndOfWord(true)

                    // get the property set of the cursor selection
                    com.sun.star.beans.XPropertySet xPropertySet =
                            xWordCursor.guno(com.sun.star.beans.XPropertySet.class )
                    // set it to bold
                    xPropertySet.setPropertyValue("CharWeight",
                            new Float( com.sun.star.awt.FontWeight.BOLD ))
                }
                catch( Exception e) {
                    e.printStackTrace(System.err)
                }
                /* SAMPLE COMMAND CODE END */

                return
            }

            if (aURL.Path.compareTo("Command1") == 0) {
                // add your own code here

                /* SAMPLE COMMAND CODE BEGIN */
                com.sun.star.frame.XController xController = m_xFrame.getController()

                //the writer controller impl supports the css.view.XSelectionSupplier interface
                XSelectionSupplier xSelectionSupplier = UnoRuntime.queryInterface(XSelectionSupplier.class, xController)

                println("xSelectionSupplier = " + xSelectionSupplier)

                if (xSelectionSupplier != null) {
                    //see section 7.5.1 of developers' guide
                    // the getSelection provides an XIndexAccess to the one or more selections
                    // XIndexAccess xIndexAccess = xSelectionSupplier.getSelection().guno(XIndexAccess.class);
                    XIndexAccess xIndexAccess = UnoRuntime.queryInterface(XIndexAccess.class, xSelectionSupplier.getSelection())

                    Integer count = wordcount(xIndexAccess)
                    // println("count = " + count)
                    println("count = " + count)
                } else {
                    println("xSelectionSupplier is null")
                    // display a message box
                    XMultiComponentFactory xMCF = m_xContext.getServiceManager()
                    Object oDesktop = null
                    try {
                        oDesktop = xMCF.createInstanceWithContext("com.sun.star.frame.Desktop", m_xContext)
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // XFrame xFrame = xDesktop.getCurrentFrame()
                    Object oToolkit = null;
                    try {
                        oToolkit = xMCF.createInstanceWithContext("com.sun.star.awt.Toolkit", m_xContext)
                    } catch (Exception e) {
                        e.printStackTrace()
                    }
                    XMessageBoxFactory xMessageBoxFactory = UnoRuntime.queryInterface(XMessageBoxFactory.class, oToolkit)
                    XWindow xWindow = m_xFrame.getContainerWindow()
                    XWindowPeer xWindowPeer = UnoRuntime.queryInterface(XWindowPeer.class, xWindow)

                    XMessageBox xMessageBox = xMessageBoxFactory.createMessageBox(xWindowPeer,
                            MessageBoxType.INFOBOX, MessageBoxButtons.BUTTONS_OK,
                            "Window Title", "Please select words to count in Writer")

                    short infoBoxResult = xMessageBox.execute()

                }
                /* SAMPLE COMMAND CODE END */

                return
            }
        }
    }

    /* SAMPLE COMMAND HELPER METHOD */
    // count the words
    Integer wordcount(XIndexAccess xIndexAccess) {

        Integer result = 0

        // iterate through each of the selections
        Integer count = xIndexAccess.getCount()
        for (int i = 0; i < count; i++) {
            // get the XTextRange of the selection
            XTextRange xTextRange = null
            try {
                xTextRange = (XTextRange) UnoRuntime.queryInterface(XTextRange.class, xIndexAccess.getByIndex(i))
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (WrappedTargetException e) {
                e.printStackTrace()
            }
            // XTextRange xTextRange = xIndexAccess.getByIndex(i).guno(XTextRange.class)

            // println("string: "+xTextRange.getString());
            // use the standard J2SE delimiters to tokenize the string
            // obtained from the XTextRange
            StringTokenizer strTok = new StringTokenizer(xTextRange.getString())
            result += strTok.countTokens()
        }

        doDisplay(result)
        return result
    }

    /* SAMPLE COMMAND HELPER METHOD */
    // display the count in a Swing dialog
    void doDisplay(Integer numWords) {

        JLabel wordsLabel = new JLabel("Word count = " + numWords)
        JButton closeButton = new JButton("Close")
        JFrame frame = new JFrame("Word Count")
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false)
            }
        });
        frame.getContentPane().setLayout(new BorderLayout())
        frame.getContentPane().add(wordsLabel, BorderLayout.CENTER)
        frame.getContentPane().add(closeButton, BorderLayout.SOUTH)
        frame.pack();
        frame.setSize(190, 90)
        frame.setLocation(430, 430)
        frame.setVisible(true)
    }

    void addStatusListener(com.sun.star.frame.XStatusListener xControl,
                                  com.sun.star.util.URL aURL) {
        // add your own code here
    }

    void removeStatusListener(com.sun.star.frame.XStatusListener xControl,
                                     com.sun.star.util.URL aURL) {
        // add your own code here
    }

    // com.sun.star.lang.XInitialization:
    void initialize(Object[] object)
            throws com.sun.star.uno.Exception {
        if (object.length > 0) {
            m_xFrame = (com.sun.star.frame.XFrame) UnoRuntime.queryInterface(
                    com.sun.star.frame.XFrame.class, object[0])
        }
    }

    // com.sun.star.frame.XDispatchProvider:
    com.sun.star.frame.XDispatch queryDispatch(com.sun.star.util.URL aURL,
                                                      String sTargetFrameName,
                                                      int iSearchFlags) {
        if (aURL.Protocol.compareTo("${project_package}.${project_lowercase_name}:") == 0) {
            if (aURL.Path.compareTo("Command0") == 0) {
                return this
            }
            if (aURL.Path.compareTo("Command1") == 0) {
                return this
            }
        }
        return null
    }

    // com.sun.star.frame.XDispatchProvider:
    com.sun.star.frame.XDispatch[] queryDispatches(
            com.sun.star.frame.DispatchDescriptor[] seqDescriptors) {
        int nCount = seqDescriptors.length
        com.sun.star.frame.XDispatch[] seqDispatcher
                = new com.sun.star.frame.XDispatch[seqDescriptors.length]

        for (int i = 0; i < nCount; ++i) {
            seqDispatcher[i] = queryDispatch(seqDescriptors[i].FeatureURL,
                    seqDescriptors[i].FrameName,
                    seqDescriptors[i].SearchFlags)
        }
        return seqDispatcher
    }

}
