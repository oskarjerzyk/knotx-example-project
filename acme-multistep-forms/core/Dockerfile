FROM knotx/knotx

## Add new dependencies to the Knot.x stack, a dependencies to your custom code
RUN apt-get update && apt-get -y install vim
COPY knotx-stack.json ${KNOTX_HOME}/knotx-stack.json
RUN knotx resolve && rm -rf ${HOME}/.m2

COPY conf ${KNOTX_HOME}/conf
COPY verticle ${KNOTX_HOME}/lib
COPY sample ${KNOTX_HOME}/sample


# Turn on debug
RUN sed -i 's/# JVM_DEBUG=/JVM_DEBUG=/g' ${KNOTX_HOME}/bin/knotx

# Suspend Knotx start
#RUN sed -i 's/suspend=n/suspend=y/g'  ${KNOTX_HOME}/bin/knotx

WORKDIR $KNOTX_HOME

EXPOSE 8092
ENTRYPOINT ["knotx"]
CMD ["run-knotx"]
