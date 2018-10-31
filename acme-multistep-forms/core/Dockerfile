FROM knotx/knotx

## Add new dependencies to the Knot.x stack, a dependencies to your custom code
RUN apt-get update && apt-get -y install vim
COPY knotx-stack.json ${KNOTX_HOME}/knotx-stack.json
RUN knotx resolve && rm -rf ${HOME}/.m2

COPY conf ${KNOTX_HOME}/conf
COPY verticle ${KNOTX_HOME}/lib

EXPOSE 8092
EXPOSE 18092

WORKDIR $KNOTX_HOME
ENTRYPOINT ["knotx"]
CMD ["run-knotx", "-cluster"]
