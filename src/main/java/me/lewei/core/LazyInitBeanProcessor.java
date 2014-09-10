package me.lewei.core;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;


public class LazyInitBeanProcessor implements BeanFactoryPostProcessor {
    private List<String> includedClasses;
    private List<String> excludedClasses;

    /**
     * Disable default pre-init singleton bean
     * @param beanFactory -
     * @throws BeansException
     */
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

            String beanClassName = beanDefinition.getBeanClassName();
            try {
                for (String package_include : includedClasses) {
                    if (beanClassName.startsWith(package_include)) {
                        boolean skip = false;
                        for (String package_exclude : excludedClasses) {
                            if (beanClassName.startsWith(package_exclude)) {
                                skip = true;
                                break;
                            }
                        }
                        if (!skip) {
                            beanDefinition.setLazyInit(true);
                        }
                        break;
                    }
                }
            } catch (Exception e) {
            	
            }

        }
    }


    public void setIncludedClasses(List<String> includedClasses) {
        this.includedClasses = includedClasses;
    }

    public void setExcludedClasses(List<String> excludedClasses) {
        this.excludedClasses = excludedClasses;
    }
}


